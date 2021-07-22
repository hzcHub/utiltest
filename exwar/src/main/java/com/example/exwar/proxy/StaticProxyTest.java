package com.example.exwar.proxy;

public class StaticProxyTest {

    public static void main(String[] args) {

        ServiceImpl service = new ServiceImpl();

        AdminServiceProxy adminServiceProxy = new AdminServiceProxy(service);
        adminServiceProxy.get();
        adminServiceProxy.send();


    }
}
