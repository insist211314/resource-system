package com.jiuzhi.common.resource.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public class DownloadResponseBo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long jobId;
    private Map<String, String> accessUrlMap;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Map<String, String> getAccessUrlMap() {
        return accessUrlMap;
    }

    public void setAccessUrlMap(Map<String, String> accessUrlMap) {
        this.accessUrlMap = accessUrlMap;
    }

    public void addAccessUrl(String key, String value){
        if(accessUrlMap==null)
            accessUrlMap = new HashMap<>();

        accessUrlMap.put(key,value);
    }

    @Override
    public String toString() {
        return "DownloadResponseBo{" +
                "jobId=" + jobId +
                ", accessUrlMap=" + accessUrlMap +
                '}';
    }
}
