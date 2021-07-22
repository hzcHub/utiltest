package com.example.consumer.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.example.provider.service.TestServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {


    @Reference
    TestServices testServices;


    @GetMapping("a")
    public String getMsg(){
        String msg = testServices.getMsg();
        return msg;
    }


}
