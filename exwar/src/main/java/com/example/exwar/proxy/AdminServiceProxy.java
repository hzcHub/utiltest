package com.example.exwar.proxy;

public class AdminServiceProxy implements Service{

    private Service service;

    public AdminServiceProxy (Service service){
        service = service;
    }


    @Override
    public void get() {
        System.out.println("代理方法get执行");
        service.get();
    }

    @Override
    public void send() {
        System.out.println("代理方法send执行");
        service.send();
    }
}
