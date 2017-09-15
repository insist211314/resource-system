package com.jiuzhi.common.resource.manage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.appleframework.cache.codis.CodisResourcePool;
import com.appleframework.cache.core.utils.SerializeUtility;
import com.jiuzhi.common.resource.entity.ImgRefer;

import redis.clients.jedis.Jedis;

@Repository("imgReferCacheManage")
public class ImgReferCacheManage {

	private static Logger logger = Logger.getLogger(ImgReferCacheManage.class); 
	
	@Resource
	private CodisResourcePool codisResourcePool;
	
	private String CODIS_KEY = "http:imge:refer";
		
	//@Override
	public List<ImgRefer> getImgRefer() {	
		
		List<ImgRefer> refers = new ArrayList<ImgRefer>() ;
		try (Jedis jedis = codisResourcePool.getResource()) {			
			refers = (List<ImgRefer>)SerializeUtility.unserialize(jedis.get(CODIS_KEY.getBytes()));
			logger.info(refers);
		}
		
		return refers;
	}
	
	public void setImgRefer(List<ImgRefer> refers) {	
		try (Jedis jedis = codisResourcePool.getResource()) {
			jedis.set(CODIS_KEY.getBytes(),SerializeUtility.serialize(refers));			
		}
	}
	

}
