package com.pro.mq.util;

import java.io.IOException;
import java.util.Properties;

public class ProperUtil {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ss.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){

        return properties.getProperty(key);
    }

}
