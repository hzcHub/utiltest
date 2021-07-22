package com.pro.sun3;

import com.pro.sun3.mapper.User11Mapper;
import com.pro.sun3.model.User11;
import org.junit.Assert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("com.pro.sun3.mapper")
public class Sun3Application {

    public static void main(String[] args) {
        SpringApplication.run(Sun3Application.class, args);
    }






}
