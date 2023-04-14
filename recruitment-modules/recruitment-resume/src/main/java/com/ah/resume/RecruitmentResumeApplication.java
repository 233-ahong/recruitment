package com.ah.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author 26471
 * @date 2023/4/12 11:16
 * @description
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RecruitmentResumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentResumeApplication.class, args);
    }
}
