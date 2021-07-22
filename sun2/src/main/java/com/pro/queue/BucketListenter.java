package com.pro.queue;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Upload;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class BucketListenter implements Runnable{

    @Override
    public void run() {

        listIncompleteUploads ();
    }

    public void listIncompleteUploads () {
        boolean found = false;
        MinioClient minioClient = MinioClientUtil.getMinioClient();
        String name = Thread.currentThread().getName();
        try {
            found = minioClient.bucketExists("my-bucketname");
            if (found) {
                while (true){
                    System.out.println("BucketListenter name = "+name+":监听中。。。。。。。");
                    Thread.sleep(2000);
                    Iterable<Result<Upload>> myObjects = minioClient.listIncompleteUploads("my-bucketname");
                    for (Result<Upload> result : myObjects) {
                        Upload upload = result.get();
                        System.out.println(upload.uploadId() + ", " + upload.objectName());
                    }
                }
            } else {
                System.out.println("my-bucketname does not exist");
            }
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
