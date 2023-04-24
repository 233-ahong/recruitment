package com.ah.common.config;

import com.ah.common.handler.DateHandler;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 26471
 * @date 2023/4/19 22:36
 * @description
 */
@Component
public class MybatisPlusConfig {

    @Resource
    private DateHandler dateHandler;

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(dateHandler);
        return globalConfig;
    }
}

