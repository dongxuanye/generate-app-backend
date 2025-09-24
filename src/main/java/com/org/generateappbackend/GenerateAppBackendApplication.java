package com.org.generateappbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan("com.org.generateappbackend.mapper")
public class GenerateAppBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateAppBackendApplication.class, args);
    }

}
