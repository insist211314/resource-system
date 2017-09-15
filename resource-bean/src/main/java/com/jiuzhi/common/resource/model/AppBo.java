package com.jiuzhi.common.resource.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/16.
 */
public class AppBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ip;
    private Integer jmxPort;
    private String appName;

    private Long jobId;

    private String notifyId;
    private String notifyType;

    private Boolean isProxy=Boolean.FALSE ;

    public AppBo(String ip, Integer jmxPort, String appName, Long jobId, String notifyId, String notifyType){
        this.ip = ip;
        this.jmxPort = jmxPort;
        this.appName = appName;
        this.jobId = jobId;
        this.notifyId = notifyId;
        this.notifyType = notifyType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public Integer getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(Integer jmxPort) {
        this.jmxPort = jmxPort;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Boolean getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(Boolean isProxy) {
        this.isProxy = isProxy;
    }
}
