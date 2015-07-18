package com.cosplay.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplay.cache.container.AbsBaseContainer;
import com.cosplay.cache.manager.CacheContainerManager;
import com.cosplay.cache.service.ICacheService;


public class BaseCacheService<K,V,T extends AbsBaseContainer<K,V>> implements ICacheService<K,V,T> {
	
	private CacheContainerManager<K,V> cacheManager =new CacheContainerManager<K,V>();

	@Override
	public void cacheInsert(String cacheKey, K key, V value ,T type) {
		cacheManager.cacheInsert(cacheKey, key, value,type);
	}

	

	@Override
	public V cacheLoad(String cacheKey, K key, T type) {
		return cacheManager.cacheLoad(cacheKey, key, type);
	}
	@Override
	public boolean cacheContainsKey(String cacheKey,K key,AbsBaseContainer<K,V> type){
		
		return cacheManager.cacheContainsKey(cacheKey, key, type);
	}

	@Override
	public boolean checkCacheKey(String cacheKey,T type) {
		return cacheManager.registerCache(cacheKey, type);
	}

}
