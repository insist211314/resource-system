package com.jiuzhi.common.resource.task;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.AbstractSimpleElasticJob;
import com.jiuzhi.common.resource.dao.ImgReferDao;
import com.jiuzhi.common.resource.entity.ImgRefer;
import com.jiuzhi.common.resource.manage.ImgReferCacheManage;

@Component("imgReferCacheTask")
public class ImgReferCacheTask extends AbstractSimpleElasticJob {

	private static final Logger logger = Logger.getLogger(ImgReferCacheTask.class);

	@Resource
	private ImgReferCacheManage imgReferCacheManage;
	
	@Resource
	private ImgReferDao imgReferDao;
	
	
	public void process(final JobExecutionMultipleShardingContext context) {

		init();
		logger.info("加载图片refer缓存" );

	}
	
	@PostConstruct
	public void init(){
		
		List<ImgRefer> refers = imgReferDao.getImgRefer();
		imgReferCacheManage.setImgRefer(refers);
		logger.info("加载图片refer缓存......,初始化......" );
	}

}
