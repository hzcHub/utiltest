package com.pro.mq.util;

public class TestPro {

    public static void main(String[] args) {
        ProperUtil properUtil = new ProperUtil();
        String value = ProperUtil.getValue("server.port");
        System.out.println(value);
    }
}
