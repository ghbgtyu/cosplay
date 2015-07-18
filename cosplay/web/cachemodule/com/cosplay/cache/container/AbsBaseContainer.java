package com.cosplay.cache.container;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.cache.config.CacheConfig;
import com.cosplay.cache.entity.CacheConfigEntity;
import com.cosplay.cache.exception.CacheNotRegisterException;

/**
 * 缓存容器基类
 * */
public abstract class AbsBaseContainer<K,V>implements Serializable{

	private static final long serialVersionUID = 3452775167591057980L;
	private CacheConfigEntity cacheConfigEntity;
	

	public AbsBaseContainer(String cacheName){
		loadCacheConfig(cacheName);
	}
	public AbsBaseContainer(){
		cacheConfigEntity = new CacheConfigEntity();
	}
	private void loadCacheConfig(String cacheName){
		try {
			cacheConfigEntity = CacheConfig.getCacheConfigByName(cacheName);
		} catch (CacheNotRegisterException e) {
			CosplayLog.error(e.getMessage());
		}
	}
	/**清理容器*/
	abstract void clean();
	/**
	 * 需要重写equals,使得该容器的实现类只存在一种
	 * */
	public abstract boolean equals(Object o);
	/**
	 * 需要重写hashCode ，使得该容器的实现类只存在一种
	 * */
	public abstract int hashCode();
	
	public CacheConfigEntity getCacheConfigEntity() {
		return cacheConfigEntity;
	}
	public abstract void cacheInsert(K key, V value);
	public abstract V cacheLoad(K key);
	public abstract boolean containsKey(K key);
	public abstract boolean containsValue(V value);
	

}
