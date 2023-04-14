package com.ah.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author 26471
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.ah.*.mapper")
@EnableFeignClients
public class RecruitmentAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentAdminApplication.class, args);
        System.out.println("人才招聘系统管理模块启动成功");
    }
}
