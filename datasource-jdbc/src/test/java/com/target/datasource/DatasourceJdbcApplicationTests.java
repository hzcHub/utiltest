package com.target.datasource;

import com.target.datasource.bean.TrainInfo;
import com.target.datasource.service.Uservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
class DatasourceJdbcApplicationTests {


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Autowired
    Uservice uservice;


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void test1(){
        List<TrainInfo> trainInfos = uservice.selectBlog2(18);
        AtomicInteger a = new AtomicInteger();

        trainInfos.forEach( trainInfo ->
                {
                    int andIncrement = a.getAndIncrement();
                    System.out.println("andIncrement = " + andIncrement);
                   // redisTemplate.opsForHash().put("train","train-"+trainInfo.getTRAINID(),trainInfo.getLINENAME());
                   // redisTemplate.opsForList().leftPush(trainInfo.getTRAINID(),trainInfo.getMETROLINEID());
                   // redisTemplate.delete(trainInfo.getTRAINID());
                    redisTemplate.opsForHash().put("train","tarininfo-"+trainInfo.getTRAINID(),trainInfo);
                }
        );

    }


    @Test
    void contextLoads() throws SQLException {


            Connection connection = dataSource.getConnection();
            System.out.println("connection = " + connection) ;


    }




}
