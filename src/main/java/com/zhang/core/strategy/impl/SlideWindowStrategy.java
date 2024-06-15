package com.zhang.core.strategy.impl;
import com.zhang.core.ControlRule;
import com.zhang.core.strategy.AbstractStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhang
 * @date 2024/5/22
 * @Description
 */
@Slf4j
public class SlideWindowStrategy extends AbstractStrategy {
    private final String LuaScriptPath = "classpath:lua/slideWindowStrategy.lua";


    @Override
    public Long controlStrategy(ControlRule controlRule) throws IOException {
        log.info("实现滑动窗口限流策略");
        return controlStrategy(controlRule, LuaScriptPath);
    }
}
