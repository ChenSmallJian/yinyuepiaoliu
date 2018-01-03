package com.whut.yinyuepiaoliu.service.impl;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.whut.yinyuepiaoliu.service.IFileService;
import com.whut.yinyuepiaoliu.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileServiceImpl implements IFileService{
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file,String path){
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        // 生成上传文件名
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名为：{}，上传的路径为：{}，新文件名为：{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            // 到这一步，文件已经上传成功

            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到FTP服务器

            targetFile.delete();
        } catch (IOException e) {
            logger.error("文件上传异常", e);
            return null;
        }
        return targetFile.getName();
    }
}