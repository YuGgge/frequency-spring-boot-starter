package com.zhang.core.strategy;

/**
 * @author zhang
 * @date 2024/6/14
 * @Description
 */
public enum StrategyType {
    /**
     * 漏桶限流策略
     */
    leaky_bucket,
    /**
     * 滑动窗口策略
     */
    slide_window
}
