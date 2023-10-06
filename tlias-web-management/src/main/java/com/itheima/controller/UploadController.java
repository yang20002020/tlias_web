package com.itheima.controller;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;


@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String username,Integer age,MultipartFile image) throws IOException {
       log.info("文件上传:{},{},{}",username,age,image);
        return Result.success();
    }
}

