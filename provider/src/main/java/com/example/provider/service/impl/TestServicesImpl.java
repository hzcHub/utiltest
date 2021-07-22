package com.example.provider.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.bean.User;
import com.example.provider.service.TestServices;


@Service
public class TestServicesImpl implements TestServices {
    @Override
    public String getMsg() {

        User u = new User();
        u.getName();
        return "MsgA啊啊啊啊11111111111";
    }


}
