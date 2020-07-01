package com.zhenL;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhenL
 * @description
 */
@SpringBootApplication
@MapperScan("com.zhenL.mapper")
public class SpringExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExampleApplication.class,args);
    }
}
