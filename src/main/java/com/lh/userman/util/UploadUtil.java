package com.lh.userman.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @description TODO
 * @date 2020/6/18
 */
public class UploadUtil {


    /**
     * 返回的是上传的文件名称
     * @param file
     * @return
     * @throws IOException
     */
    public static String upload(MultipartFile file)throws IOException{
        //返回上传的文件名称
        String fileName="";
        //判断对象不为空
        if (file != null) {
            //判断文件不为空
            if (!file.isEmpty()) {
                //获取文件名称
                String originalFilename = file.getOriginalFilename();
                //新文件名称
                fileName = UUID.randomUUID() + "_" + originalFilename;
                //创建新文件对象
                File destFile = new File("D://pic//", fileName);
                //判断目标文件存在否
                if (!destFile.getParentFile().exists()) {
                    //创建目录
                    destFile.mkdirs();
                }
                //执行拷贝过程
                file.transferTo(destFile);
            }
        }
        return fileName;
    }

    /**
     * 上传文件名称集合
     * @param files
     * @return
     * @throws IOException
     */
    public static List<String> upload(List<MultipartFile> files) throws IOException {
        List<String> list = new ArrayList();
        for(MultipartFile file:files) {
            //判断对象不为空
            if (file != null) {
                //判断文件不为空
                if (!file.isEmpty()) {
                    //获取文件名称
                    String originalFilename = file.getOriginalFilename();
                    //新文件名称
                    String fileName = UUID.randomUUID() + "_" + originalFilename;
                    //创建新文件对象
                    File destFile = new File("D://pic//", fileName);
                    //判断目标文件存在否
                    if (!destFile.getParentFile().exists()) {
                        //创建目录
                        destFile.mkdirs();
                    }
                    //执行拷贝过程
                    file.transferTo(destFile);
                    list.add(fileName);
                }
            }
        }
        return list;
    }
}
