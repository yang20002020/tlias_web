package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {
//    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//    private String bucketName = "tlisa-web";
      @Value("${aliyun.oss.endpoint}")
      private String endpoint ;

      @Value("${aliyun.oss.bucketName}")
      private String bucketName ;

    /**
     * 实现上传图片到OSS
     */
    public String upload_1(MultipartFile file) throws IOException, ClientException {
        //1. 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        //2. 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //3.上传文件到 OSS
   /**********************************/
        // 3.1 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        String accessKeyId = credentialsProvider.getCredentials().getAccessKeyId();
        String secretAccessKey = credentialsProvider.getCredentials().getSecretAccessKey();
        // 3.2 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,secretAccessKey);
        ossClient.putObject(bucketName, fileName, inputStream);
   /**********************************/
        //文件访问路径  https://tlisa-web.oss-cn-beijing.aliyuncs.com/1.jpg
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

    public String upload(MultipartFile file) throws IOException, ClientException {
        //1. 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        //2. 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //3.上传文件到 OSS
        /**********************************/
        // 3.1 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 3.2 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint,credentialsProvider);
        ossClient.putObject(bucketName, fileName, inputStream);
        /**********************************/
        //文件访问路径  https://tlisa-web.oss-cn-beijing.aliyuncs.com/1.jpg
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
