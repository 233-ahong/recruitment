package com.ah.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 26471
 * @date 2023/4/9 21:56
 * @description
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RecruitmentFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentFileApplication.class, args);
    }
}
