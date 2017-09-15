package com.jiuzhi.common.resource.cache;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.appleframework.cache.codis.CodisResourcePool;
import com.jiuzhi.common.resource.manage.ImgReferCacheManage;

import redis.clients.jedis.Jedis;

@Component("exceptionProcessCache")
public class ExceptionProcessCache {

	private static Logger logger = Logger.getLogger(ImgReferCacheManage.class);

	@Resource
	private CodisResourcePool codisResourcePool;

	public void set(String buinessId, Long id) {
		try (Jedis jedis = codisResourcePool.getResource()) {
			logger.info("exceptionProcessCache.set() buinessId:" + buinessId + " id:" + id);
			jedis.hincrBy(buinessId, id.toString(), 1);
		}
	}

	public int get(String buinessId, Long id) {
		int num = 0;
		try (Jedis jedis = codisResourcePool.getResource()) {
			if (jedis.hexists(buinessId, id.toString())) {
				num = Integer.parseInt(jedis.hget(buinessId, id.toString()));
			} else {
				jedis.hincrBy(buinessId, id.toString(), 1);
				num = 1;
			}
		}
		return num;
	}

	public void remove(String buinessId, Long id) {
		try (Jedis jedis = codisResourcePool.getResource()) {
			if (jedis.hexists(buinessId, id.toString())) {
				jedis.hdel(buinessId, id.toString());
			}
		}
	}

}
