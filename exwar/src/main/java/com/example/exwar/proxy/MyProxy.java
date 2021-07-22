package com.example.exwar.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy<T> implements InvocationHandler {

    private T target;

    public MyProxy(T target){
        target = target;
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理method = " + method.getName());
        return method.invoke(target,args);
    }



}
