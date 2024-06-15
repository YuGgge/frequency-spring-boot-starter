package com.zhang.core.strategy;

import com.zhang.core.ControlRule;

import java.io.IOException;

/**
 * @author zhang
 * @date 2024/5/22
 * @Description
 */
public interface ControlManager {
    /**
     * 策略控制
     * @param controlRule
     * @throws IOException
     */
    Long controlStrategy(ControlRule controlRule) throws IOException;
}
