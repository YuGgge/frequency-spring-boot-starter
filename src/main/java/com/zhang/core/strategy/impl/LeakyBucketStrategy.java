package com.zhang.core.strategy.impl;

import com.zhang.core.ControlRule;
import com.zhang.core.strategy.AbstractStrategy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class LeakyBucketStrategy extends AbstractStrategy {

    private String LuaScriptPath = "classpath:lua/leakyBucketStrategy.lua";


    @Override
    public Long controlStrategy(ControlRule controlRule) throws IOException {
        log.info("实现漏桶限流策略");
        return controlStrategy(controlRule, LuaScriptPath);
    }



}