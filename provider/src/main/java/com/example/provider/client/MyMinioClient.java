package com.example.provider.client;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyMinioClient {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MyMinioClient.class);

//minio:
//  endpoint: http://localhost:9000 #Minio服务所在地址
//  bucketName: tcc #存储桶名称
//  accessKey: tanchuntcc #访问的key
//  secretKey: tanchuntcc #访问的秘钥
    //http://123.56.202.150:9000/rtoms/data/video/liuming20210204113004_x.mp4

    private  static final String ENDPOINT = "http://123.56.202.150:9000";

    //private  static final String BUCKETNAME = "/rtoms/data/video/";
    private  static final String BUCKETNAME = "rtoms";

    private  static final String ACCESSKEY = "minio";

    private  static final String SECRETKEY = "minio123456";


    //@RequestMapping("")
    //public void downloadFiles(@RequestParam("filename") String filename/*, HttpServletResponse httpResponse*/) {
    public static void downloadFiles(String filename/*, HttpServletResponse httpResponse*/) {


        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESSKEY, SECRETKEY);
            InputStream object = minioClient.getObject(BUCKETNAME, filename);
            byte buf[] = new byte[1024];
            int length = 0;
            // httpResponse.reset();

            // httpResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // httpResponse.setContentType("application/octet-stream");
            // httpResponse.setCharacterEncoding("utf-8");
            //  OutputStream outputStream = httpResponse.getOutputStream();

            OutputStream outputStream = new FileOutputStream(new File("D:\\a.mp4"));
            while ((length = object.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        downloadFiles("/data/video/liuyuxin20210201234940.mp4");
    }

}
