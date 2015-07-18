package com.cosplay.cache.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




import com.alibaba.fastjson.JSON;
import com.cosplay.base.util.ConfigureUtil;
import com.cosplay.base.util.StringUtil;
import com.cosplay.bus.config.BusConfig;
import com.cosplay.bus.log.CosplayLog;
import com.cosplay.cache.entity.CacheConfigEntity;
import com.cosplay.cache.exception.CacheNotRegisterException;


public class CacheConfig {
	private static List<String> cacheProNameList = new ArrayList<String>();
	static{
		//注册缓存类型
		
		// 注册模块用户名
		Map<String, String> cacheMap = ConfigureUtil.getPropsMap(BusConfig.CACHE_MANAGER_CONFIG_PATH);
		for (Entry<String, String> entry : cacheMap.entrySet()) {
			cacheProNameList.add(entry.getKey());
		}
	}
	
	public static CacheConfigEntity getCacheConfigByName(String key) throws CacheNotRegisterException{
		
		CacheConfigEntity configEntity = new CacheConfigEntity();
		if( StringUtil.strIsEmpty(key)){
			CosplayLog.error("缓存 key is null :"+key);
			return configEntity;
		}
		//没有注册
		if( !cacheProNameList.contains(key) ){
			 throw new CacheNotRegisterException("缓存配置没有找到:"+key);
		}
		String jsonString = ConfigureUtil.getProp(BusConfig.CACHE_MANAGER_CONFIG_PATH, key);
		if( StringUtil.strIsEmpty(jsonString)){
			CosplayLog.error("缓存配置为空，请尽快解决     cacheName :"+key);
			return configEntity;
		}
		configEntity = JSON.parseObject(jsonString, CacheConfigEntity.class);
		return configEntity;
	}
	/**
	 * 获取已注册cache类型
	 * */
	public static List<String> getCacheProList(){
		return cacheProNameList;
	}
}
