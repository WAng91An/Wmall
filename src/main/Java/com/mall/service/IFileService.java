package com.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 王乾 on 2017/12/21.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);

}
