package com.jiuzhi.common.resource.task;


import com.jiuzhi.common.resource.crawling.ResourceJobNotifyProcess;
import com.jiuzhi.common.resource.manage.FileJobManage;
import com.jiuzhi.common.resource.model.FileJobBo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/3/20.
 */
@Component("resourceJobNotifyTask")
public class ResourceJobNotifyTask implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    FileJobManage fileJobManage;

    @Resource
    private ThreadPoolTaskExecutor crawlingExecutor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                List<FileJobBo> jobs = fileJobManage.findByCompletedJob();
                for(FileJobBo job : jobs) {
                    crawlingExecutor.execute(new ResourceJobNotifyProcess(job));
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 60 * 1000, 120 * 1000);

//        List<FileJobBo> jobs = fileJobManage.findByCompletedJob();
//        for(FileJobBo job : jobs) {
//            crawlingExecutor.execute(new ResourceJobNotifyProcess(job));
//        }
    }
}
