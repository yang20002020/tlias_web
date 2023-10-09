package com.itheima.controller;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {


    //本地存储文件
    @PostMapping("/upload_1")
    public Result upload_1(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传: {}, {}, {}", username, age, image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();
        //将文件存储在服务器的磁盘目录中 C:\images
        image.transferTo(new File("C:\\Users\\26286\\Desktop\\images\\" + originalFilename));
        return Result.success();
    }

    //本地存储文件
    @PostMapping("/upload_2")
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传: {}, {}, {}", username, age, image);
        //获取原始文件名 - 1.jpg  123.0.0.jpg
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名 (不能重复) - uuid(通用唯一识别码) de49685b-61c0-4b11-80fa-c71e95924018
        int index = originalFilename.lastIndexOf(".");
        //extname = .txt
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名: {}", newFileName);

        //将文件存储在服务器的磁盘目录中 E:\images
        image.transferTo(new File("C:\\Users\\26286\\Desktop\\images\\" + newFileName));

        return Result.success();
    }



        @Autowired
        private AliOSSUtils aliOSSUtils;

        @PostMapping("/upload")
        public Result upload_2(MultipartFile image) throws IOException, ClientException {

            log.info("文件上传，文件名:{}",image.getOriginalFilename());
            //调用阿里云OSS工具类，将上传上来的文件存入阿里云
            String url = aliOSSUtils.upload(image);
            log.info("文件上传完成，文件访问的url:{}",url);
            //将图片上传完成后的url返回，用于浏览器回显展示
            return Result.success(url);
        }



}