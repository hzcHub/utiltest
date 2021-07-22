package com.pro.queue;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class UploadUtil implements Runnable {

    private String lock;
    private int countnu = 0;
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    @Override
    public void run() {
        uploadObject();
    }

    public void uploadObject() {
        try {

                while (countnu < 100) {
                    rwl.writeLock().lock();
                    System.out.println("currentThread1 = " + Thread.currentThread().getName());
                    if(countnu>=100) break;
                    System.out.println("currentThread2 = " + Thread.currentThread().getName()+"count:"+countnu);
                   /* MinioClient minioClient = MinioClientUtil.getMinioClient();
                    // 检查存储桶是否已经存在
                    boolean isExist = minioClient.bucketExists("my-bucketname");
                    if (isExist) {

                    } else {
                        // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                        minioClient.makeBucket("my-bucketname");
                    }
                    File file = new File("C:\\Users\\Administrator\\Desktop/new.properties");
                    //File file = new File("E:\\qycache\\download\\霍比特人1：意外之旅/霍比特人1：意外之旅-蓝光4K.qsv");
                    String fileName = file.getName();
                    FileInputStream stream = new FileInputStream(file);

                    PutObjectOptions options =
                            new PutObjectOptions(-1, 10485760);
                    // 使用putObject上传一个文件到存储桶中。
                    minioClient.putObject("my-bucketname", countnu + fileName, stream, options);
                    stream.close();*/

                  //  System.out.println("文件" + countnu + fileName + "上传成功;" + "currentThread = " + Thread.currentThread().getName());
                    System.out.println();
                    this.countnu = countnu + 1;
                    rwl.writeLock().unlock();
                }

        } finally {
            rwl.writeLock().unlock();
        }
    }


}
