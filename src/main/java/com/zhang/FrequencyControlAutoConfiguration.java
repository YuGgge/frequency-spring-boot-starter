package com.zhang;

import com.zhang.core.aspect.ControlAspect;
import com.zhang.core.ControlProperties;
import com.zhang.core.strategy.impl.LeakyBucketStrategy;
import com.zhang.core.strategy.impl.SlideWindowStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableConfigurationProperties(ControlProperties.class)
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.zhang"})
public class FrequencyControlAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ControlAspect.class)
    public ControlAspect ControlAspect() {
        return new ControlAspect();
    }

    @Bean
    @ConditionalOnProperty(prefix = "frequency-control", name = "strategy-type", havingValue = "slide_window")
    public SlideWindowStrategy SlideWindowStrategy() {

        return new SlideWindowStrategy();
    }

    @Bean
    @ConditionalOnProperty(prefix = "frequency-control", name = "strategy-type", havingValue = "leaky_bucket")
    public LeakyBucketStrategy LeakyBucketStrategy() {
        return new LeakyBucketStrategy();
    }

}
