package com.target.datasource.controller;


import com.example.starter.HelloService;
import com.target.datasource.mapper.UserMapper;
import com.target.datasource.service.Uservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    Uservice uservice;

    @Autowired
    HelloService helloService;

    @GetMapping("/a/{id}")
    public List getAll(@PathVariable("id") Integer id){

        return  uservice.selectBlog(id);
    }


    @GetMapping("/b/{a}")
    public String getMsg(@PathVariable("a") String a){
        String msg = helloService.getMsg(a);
        return msg;
    }


    public static void main(String[] args) {

        for (char ch = 'A'; ch < 'Z'; ch++) {
            System.out.println("ch = " + ch);
        }


    }
}
