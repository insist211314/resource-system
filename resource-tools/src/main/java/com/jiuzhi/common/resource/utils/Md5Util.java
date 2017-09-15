package com.jiuzhi.common.resource.utils;



import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/11.
 */
public class Md5Util {

    public static String md5DigestByFile(String filePath){
        if(!StringUtils.hasText(filePath))
            throw new RuntimeException("filePath为null!");
        File f = new File(filePath);
        if(!f.exists())
            throw new RuntimeException("文件不存在!");
        FileInputStream is = null;
        try {
            is = new FileInputStream(f);
            return DigestUtils.md5DigestAsHex(is);
        }catch (Exception e){
            throw new RuntimeException("获取md5值失败 filepath=" + filePath, e);
        }finally {
            if(is !=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String md5DigestByString(String content){
        if(!StringUtils.hasText(content))
            throw new RuntimeException("filePath为null!");
        try {
            return DigestUtils.md5DigestAsHex(content.getBytes("utf-8"));
        } catch (Exception e) {
            throw new RuntimeException("获取md5值失败 content=" + content, e);
        }
    }

}
