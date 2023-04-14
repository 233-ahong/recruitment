package com.ah.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 26471
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RecruitmentGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentGatewayApplication.class, args);
        System.out.println("人才招聘系统网关服务启动成功");
    }
}
