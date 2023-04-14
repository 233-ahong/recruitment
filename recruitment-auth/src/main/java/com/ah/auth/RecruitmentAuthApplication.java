package com.ah.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author 26471
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.ah")
public class RecruitmentAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentAuthApplication.class, args);
    }
}
