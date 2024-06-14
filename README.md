# frequency-spring-boot-starter
## 1. 项目结构:

```
src
        └─main
            ├─java
            │  └─com
            │      └─zhang
            │          ├─strategy  ##这个文件下主要用于实现限流策略
            │          │  │  ControlManager.java   ##接口
            │          │  │  
            │          │  └─impl
            │          │          SlideWindowStrategy.java   ##接口实现滑动窗口限流
            │          │          
            │          ├─aspect
            │          │      ControlAspect.java    ##注解切入处理
            │          │      FrequencyControl.java     ##自定义限流注解
            │          │      
            │          ├─config
            │          │      FrequencyControlAutoConfiguration.java    ##自动装配类
            │          │      RedisConfig.java
            │          │      
            │          ├─core
            │          │      ControlProperties.java    ##核心配置类，可以在配置中自定义限流规则
            │          │      ControlRule.java    ##限流规则整理
            │          │      
            │          ├─exception
            │          │      ControlException.java    ##限流异常处理
            │          │      
            │          └─utils
            │                  RedisUtil.java    
            │                  WebUtil.java    
            │                  
            └─resources
                │  application-aliyun.yml
                │  application-local.yml
                │  application.yml
                │  
                ├─META-INF
                │      spring.factories
                │      
                └─script
                        SlideWindowStrategy.lua   ##使用lua脚本保证redis的频率计数的原子性
```

## 2.核心配置使用
![img_1.png](img_1.png)

## 3.项目实现

1. 利用自动装配实现starter
2. 使用注解+Aop实现对接口的限流
3. 限流策略实现滑动窗口：
    1. 也可以通过抽象类实现多种限流规则，可以由核心配置设置
4. 使用lua脚本保证redis的频率计数的原子性：
    1. lua的语法就是简单了解了一下call函数，实现了查询增加删除
5. 实现限流规则可以由核心配置设置，注解的规定大于配置


