package com.example.exwar.proxy;

import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static void main(String[] args) {


        User u =()->{
            System.out.println("lamaaa");
        };

        u.out();
        One one = new One();

        Two two = new Two();
        InvocationHandler handler = new MyProxy<>(two);

        User user = (User) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{User.class}, handler);

        user.out();

    }
}
