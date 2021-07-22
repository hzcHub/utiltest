package com.pro.mq.rec;

import java.io.File;

public class Digui{

    public static void main(String[] args) {
        File file = new File("F:\\apache-tomcat-8.0.29\\apache-tomcat-8.0.29");
        getAllFilePath(file);
    }

    public static void getAllFilePath(File dir){
        File[] files=dir.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()){
               // System.out.println(files[i].getPath());
                //这里面用了递归的算法
                getAllFilePath(files[i]);

            } else {
                if(files[i].getPath().indexOf(".txt")!=-1){
                    System.out.println(files[i].getPath());
                }

            }
        }
    }

}