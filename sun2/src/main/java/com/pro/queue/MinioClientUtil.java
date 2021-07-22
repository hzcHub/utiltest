package com.pro.queue;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;


public class MinioClientUtil {

    private static String endPoint ="http://192.168.1.108:9000";
    private static String accessKey ="minioadmin";
    private static String secretKey ="minioadmin";
    private static MinioClient minioClient = null;

    static {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        try {
            minioClient = new MinioClient(endPoint, accessKey, secretKey);
            System.out.println("创建 minioClient 完成");
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }

    public static MinioClient getMinioClient() {
        return minioClient;
    }


}
