package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableCaching
@EnableScheduling
@EntityScan
@SpringBootApplication
public class XsyBaseDeemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XsyBaseDeemoApplication.class, args);
    }

}
