package com.whut.yinyuepiaoliu.service.impl;

import com.google.common.collect.Lists;
import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.service.IFileService;
import com.whut.yinyuepiaoliu.util.FTPUtil;
import com.whut.yinyuepiaoliu.util.PropertiesUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path,String type) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 生成上传文件名
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
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

            switch(type){
                case Const.File_save_to.SAVE_TO_ICON :
                    FTPUtil.uploadFile(Lists.newArrayList(targetFile),Const.File_save_to.SAVE_TO_ICON);
                    break;
                default:
                    break;
            }
            //已经上传到FTP服务器

            targetFile.delete();
        } catch (IOException e) {
            logger.error("文件上传异常", e);
            return null;
        }
        return targetFile.getName();
    }

    public boolean delete(String url){
        boolean res;
        String tmp = url.replaceAll(PropertiesUtil.getProperty("ftp.server.http.prefix"),"");
        String fileName = tmp.substring(tmp.lastIndexOf("/")+1);
        String path = tmp.substring(0,tmp.lastIndexOf("/"));

        try {
            res = FTPUtil.deleteFile(path,fileName);
        } catch (IOException e) {
            logger.error("文件删除异常", e);
            return false;
        }
        return res;
    }

    @Test
    public void deleteTest(){
        delete("http://img.lixiaofang.cn/icon/a65739fc-5248-42f4-836a-944c8cc718c2.jpg");
    }
}