package com.jiuzhi.common.resource.entity;

import java.io.Serializable;
import java.util.Date;

public class FileJob implements Serializable {
    private Long id;

    private String businessType;

    private String businessId;

    private String businessApp;

    private Integer taskCount;

    private Integer taskWaitCount;

    private Integer notifyRetryCount;

    private Short notifyStatus;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;

    private String failProcessIds;

    private String notifyException;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getBusinessApp() {
        return businessApp;
    }

    public void setBusinessApp(String businessApp) {
        this.businessApp = businessApp == null ? null : businessApp.trim();
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getTaskWaitCount() {
        return taskWaitCount;
    }

    public void setTaskWaitCount(Integer taskWaitCount) {
        this.taskWaitCount = taskWaitCount;
    }

    public Integer getNotifyRetryCount() {
        return notifyRetryCount;
    }

    public void setNotifyRetryCount(Integer notifyRetryCount) {
        this.notifyRetryCount = notifyRetryCount;
    }

    public Short getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Short notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public String getFailProcessIds() {
        return failProcessIds;
    }

    public void setFailProcessIds(String failProcessIds) {
        this.failProcessIds = failProcessIds == null ? null : failProcessIds.trim();
    }

    public String getNotifyException() {
        return notifyException;
    }

    public void setNotifyException(String notifyException) {
        this.notifyException = notifyException == null ? null : notifyException.trim();
    }
}