package com.jiuzhi.common.resource.model;

import com.jiuzhi.common.resource.entity.FileJob;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/3/16.
 */
public class FileJobBo extends FileJob {


    public void addFailProcessId(Long id){
        if(StringUtils.hasText(getFailProcessIds())){
            setFailProcessIds(getFailProcessIds() + "," + id);
        }else{
            setFailProcessIds(id + "");
        }
    }
}
