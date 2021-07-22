package com.example.exwar.proxy;

import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceImpl implements Service{
    @Override
    public void get() {
        System.out.println("get 运行方法");
    }

    @Override
    public synchronized void send() {

        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();

        System.out.println("send 运行方法");
    }
}
