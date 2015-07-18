package com.cosplay.thread.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cosplay.base.util.ConfigureUtil;
import com.cosplay.base.util.StringUtil;
import com.cosplay.bus.config.BusConfig;
import com.cosplay.bus.log.CosplayLog;
import com.cosplay.cache.exception.CacheNotRegisterException;
import com.cosplay.thread.entity.ThreadConfigEntity;


public class ThreadConfig {
	private static Map<String,ThreadConfigEntity> threadConfigMap = new HashMap<String,ThreadConfigEntity>();
	static{
		//注册缓存类型
		
		// 注册模块用户名
		Map<String, String> cacheMap = ConfigureUtil.getPropsMap(BusConfig.THREAD_MANAGER_CONFIG_PATH);
		for (Entry<String, String> entry : cacheMap.entrySet()) {
			threadConfigMap.put(entry.getKey(), JSONObject.parseObject(entry.getValue(), ThreadConfigEntity.class));
		}
	}
	
	public static ThreadConfigEntity getCacheConfigByName(String key) throws CacheNotRegisterException{
		
		ThreadConfigEntity configEntity = new ThreadConfigEntity();
		if( StringUtil.strIsEmpty(key)){
			CosplayLog.error(" ThreadConfig key is null :"+key);
			return configEntity;
		}
		//没有注册
		if( !threadConfigMap.containsKey(key) ){
			 throw new CacheNotRegisterException("ThreadConfig 配置没有找到:"+key);
		}
		
		return threadConfigMap.get(key);
	}
	/**
	 * 获取已注册线程类型
	 * */
	public static Map<String,ThreadConfigEntity> getThreadProList(){
		return threadConfigMap;
	}
}
