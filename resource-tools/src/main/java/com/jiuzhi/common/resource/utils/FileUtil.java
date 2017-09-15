package com.jiuzhi.common.resource.utils;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2.
 */
public class FileUtil {

    public static Map<String, String> contentType = new HashMap<>();

    static{
        contentType.put("image/gif", "gif");
        contentType.put("image/jpeg", "jpg");
        contentType.put("image/png", "png");
        contentType.put("video/mpeg4", "mp4");
        contentType.put("video/mp4", "mp4");
        contentType.put("audio/mp4", "mp4");
    }

    public static String getFileExtension(String url){
        if(!StringUtils.hasText(url))
            return null;

        if(url.indexOf("?")!=-1)
            url = url.substring(0, url.indexOf("?"));

        if(url.lastIndexOf("/")!=-1)
            url = url.substring(url.lastIndexOf("/"), url.length());

        if(url.lastIndexOf(".")!=-1)
            return url.substring(url.lastIndexOf(".")+1);

        return null;
    }

    public static void main(String[] args) {
        String a = "http://ss4.sinaimg.cn/thumbnail/4914062bx7cbd2a0476f3&690";
        System.out.println(getFileExtension(a));
    }

    public static String getFileExtension(String... urls){
        if(ArrayUtils.isEmpty(urls))
            return null;
        for(String url : urls){
            String extensionName = getFileExtension(url);
            if(StringUtils.hasText(extensionName))
                return extensionName;
        }
        return null;
    }

}
