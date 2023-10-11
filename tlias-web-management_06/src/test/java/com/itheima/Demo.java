package com.itheima;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.FileInputStream;
import java.io.InputStream;

public class Demo {

    public static void main(String[] args) throws Exception {
        // Endpoint�Ի���1�����ݣ�Ϊ��������Region�밴ʵ�������д��
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // �ӻ��������л�ȡ����ƾ֤�����б�����ʾ��֮ǰ����ȷ�������û�������OSS_ACCESS_KEY_ID��OSS_ACCESS_KEY_SECRET��
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // ��дBucket���ƣ�����examplebucket��
        String bucketName = "tlisa-web";
        // ��дObject����·��������·���в��ܰ���Bucket���ƣ�����exampledir/exampleobject.txt��
        String objectName = "1.jpg";
        // ��д�����ļ�������·��������D:\\localpath\\examplefile.txt��
        // ���δָ������·������Ĭ�ϴ�ʾ������������Ŀ��Ӧ����·�����ϴ��ļ�����
        String filePath= "C:\\Users\\26286\\Desktop\\images\\1.jpg";

        // ����OSSClientʵ����
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // ����PutObjectRequest����
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // ����PutObject����
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
