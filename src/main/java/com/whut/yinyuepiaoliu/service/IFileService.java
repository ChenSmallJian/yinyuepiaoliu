package com.whut.yinyuepiaoliu.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String upload(MultipartFile file, String path, String type);

    boolean delete(String url);
}