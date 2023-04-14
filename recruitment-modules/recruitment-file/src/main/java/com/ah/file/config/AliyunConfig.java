package com.ah.file.config;

import com.ah.file.utils.AliyunUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author 26471
 * @date 2023/4/10 12:39
 * @description
 */
@Configuration
public class AliyunConfig {
    @Resource
    AliyunUtils aliyunUtils;

    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(aliyunUtils.getEndpoint(), aliyunUtils.getAccessKey(), aliyunUtils.getSecretKey());
    }
}
