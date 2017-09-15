package com.jiuzhi.common.resource.crawling;

import com.appleframework.core.utils.SpringUtility;
import com.jiuzhi.common.resource.model.FileDownProcessBo;
import com.jiuzhi.common.resource.service.ResourceCrawlingService;
import org.redisson.Redisson;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Created by Administrator on 2016/9/21.
*/
public class ResourceDownProcess implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ResourceDownProcess.class);

    private ResourceCrawlingService resourceCrawlingService = (ResourceCrawlingService) SpringUtility.getBean("resourceCrawlingService");

    private Redisson crawlingRedisson = (Redisson) SpringUtility.getBean("crawlingRedisson");

    private static final String LOCK_KEY = "RESOURCE:DOWNLOAD:";

    private FileDownProcessBo process;

    public ResourceDownProcess(FileDownProcessBo process){
        this.process = process;
    }

    @Override
    public void run() {

        String lockKey = LOCK_KEY + process.getId();

        RLock lock = crawlingRedisson.getLock(lockKey);

        if(!lock.tryLock()){
            return;
        }
        try{
            resourceCrawlingService.crawlingResource(process);
        }catch (Exception e){
            logger.error("资源下载失败！ lockKey="+lockKey, e);
        } finally {
            lock.unlock();
        }

    }


}
