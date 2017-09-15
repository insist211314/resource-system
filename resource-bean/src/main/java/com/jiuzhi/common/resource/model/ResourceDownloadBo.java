package com.jiuzhi.common.resource.model;

import com.jiuzhi.common.resource.constant.FileType;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ResourceDownloadBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;

    @Override
    public String toString() {
        return "ResourceDownloadBo{" +
                "key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", fileType='" + fileType + '\'' +
                ", accessUrl='" + accessUrl + '\'' +
                ", params='" + params + '\'' +
                '}';
    }

    private String url;
    private String fileType;
    private String accessUrl;
    private String params;

    public ResourceDownloadBo(String key, String url, String fileType, String accessUrl, String params){
        this.key = key;
        this.url = url;
        this.fileType = fileType;
        this.accessUrl = accessUrl;
        this.params = params;
    }

    public ResourceDownloadBo(String key, String url, String fileType, String accessUrl){
        this(key, url, fileType, accessUrl, null);
    }

    public ResourceDownloadBo(String key, String url, String fileType){
        this(key, url, fileType, null);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
