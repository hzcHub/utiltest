package com.target.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@MapperScan({"com.target.datasource.mapper"})
@SpringBootApplication
@EnableCaching
public class DatasourceJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceJdbcApplication.class, args);
    }

}
