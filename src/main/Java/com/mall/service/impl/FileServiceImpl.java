package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.service.IFileService;
import com.mall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 王乾 on 2017/12/21.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 文件上传
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("文件开始上传，上传文件的文件名:{},上传的路径为:{},新文件名为:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            // 文件已经上传成功了
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            // 已经targetFile上传到FTP服务器上面
            targetFile.delete();
            //上传完后，删除upload下面的文件

        } catch (IOException e) {
            logger.error("文件上传异常",e);
            return null;
        }
        return targetFile.getName();

    }
}
