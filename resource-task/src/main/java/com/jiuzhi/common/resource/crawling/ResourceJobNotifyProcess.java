package com.jiuzhi.common.resource.crawling;

import com.appleframework.core.utils.SpringUtility;
import com.jiuzhi.common.resource.model.FileJobBo;
import com.jiuzhi.common.resource.notify.JmxNotifyServiceImpl;
import org.redisson.Redisson;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/3/24.
 */
public class ResourceJobNotifyProcess implements Runnable  {

    private static final Logger logger = LoggerFactory.getLogger(ResourceDownProcess.class);

    private JmxNotifyServiceImpl jmxNotifyService = (JmxNotifyServiceImpl) SpringUtility.getBean("jmxNotifyService");

    private Redisson crawlingRedisson = (Redisson) SpringUtility.getBean("crawlingRedisson");

    private static final String LOCK_KEY = "RESOURCE:NOTIFY:";

    private FileJobBo job;

    public ResourceJobNotifyProcess(FileJobBo job){
        this.job = job;
    }

    @Override
    public void run() {
        String lockKey = LOCK_KEY + job.getId();

        RLock lock = crawlingRedisson.getLock(lockKey);

        if(!lock.tryLock()){
            return;
        }
        try{
            jmxNotifyService.notify(job);
        }catch (Exception e){
            logger.error("通知失败！ lockKey="+lockKey, e);
        } finally {
            lock.unlock();
        }
    }
}
