package com.jiuzhi.common.resource.entity;

import java.io.Serializable;
import java.util.Date;

public class FileDownProcess implements Serializable {
    private Long id;

    private String downUrlMd5;

    private String downUrl;

    private String fileMd5;

    private String accessUrlMd5;

    private String accessUrl;

    private String type;

    private String extension;

    private Short status;

    private String fileDiskUrl;

    private Date lastDownTime;

    private Integer retryCount;

    private String params;

    private Date createTime;

    private Date updateTime;

    private Boolean isProxy;

    private String excInfo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDownUrlMd5() {
        return downUrlMd5;
    }

    public void setDownUrlMd5(String downUrlMd5) {
        this.downUrlMd5 = downUrlMd5 == null ? null : downUrlMd5.trim();
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl == null ? null : downUrl.trim();
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5 == null ? null : fileMd5.trim();
    }

    public String getAccessUrlMd5() {
        return accessUrlMd5;
    }

    public void setAccessUrlMd5(String accessUrlMd5) {
        this.accessUrlMd5 = accessUrlMd5 == null ? null : accessUrlMd5.trim();
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl == null ? null : accessUrl.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getFileDiskUrl() {
        return fileDiskUrl;
    }

    public void setFileDiskUrl(String fileDiskUrl) {
        this.fileDiskUrl = fileDiskUrl == null ? null : fileDiskUrl.trim();
    }

    public Date getLastDownTime() {
        return lastDownTime;
    }

    public void setLastDownTime(Date lastDownTime) {
        this.lastDownTime = lastDownTime;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(Boolean isProxy) {
        this.isProxy = isProxy;
    }

    public String getExcInfo() {
        return excInfo;
    }

    public void setExcInfo(String excInfo) {
        this.excInfo = excInfo == null ? null : excInfo.trim();
    }
}