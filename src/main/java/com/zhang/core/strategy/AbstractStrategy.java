package com.zhang.core.strategy;

import com.zhang.core.ControlRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author zhang
 * @date 2024/6/14
 * @Description
 */
@Component
public abstract class AbstractStrategy implements ControlManager{

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加载lua脚本
     * @param path
     * @return
     * @throws IOException
     */
    protected String loadLuaScript(String path) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
        Resource[] resource = resolver.getResources(path);
        String luaScriptContent = StreamUtils.copyToString(resource[0].getInputStream(), StandardCharsets.UTF_8);
        return luaScriptContent;
    }

    /**
     * 频率控制策略
     * @param controlRule
     * @return
     */
    public Long controlStrategy(ControlRule controlRule, String LuaScriptPath) throws IOException {
        /**
         * lua脚本操作redis
         */
        String LuaScript = loadLuaScript(LuaScriptPath);
        RedisScript<Long> redisLuaScript = new DefaultRedisScript<>(LuaScript, Long.class);
        List<Object> keys = Collections.singletonList(controlRule.getPrefix());
        //获得当前时间戳，单位为毫秒
        long currentTimeMillis = System.currentTimeMillis();
        return (Long) redisTemplate.execute(
                redisLuaScript, keys,
                controlRule.getMaxCount(),
                controlRule.getTimeRange(),
                currentTimeMillis);
    }
}
