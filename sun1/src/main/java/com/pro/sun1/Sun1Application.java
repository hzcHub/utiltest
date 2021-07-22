package com.pro.sun1;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@MapperScan("com.pro.sun1.mapper")
public class Sun1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sun1Application.class, args);
    }

    @GetMapping("test")
    public String getContent(){

        return "1244232221";
    }
}
