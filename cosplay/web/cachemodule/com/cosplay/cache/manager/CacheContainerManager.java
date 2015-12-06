package com.cosplay.cache.manager;


import com.cosplay.cache.container.AbsBaseContainer;

/**
 * 缓存容器管理器
 * */
public class CacheContainerManager<K,V> {
	
	private  CacheContext<K,V> cacheContext =new CacheContext<K,V>();
	
	public  void cacheInsert(String cacheKey, K key, V value,AbsBaseContainer<K,V> type){
			
		cacheContext.cacheInsert(cacheKey, key, value, type);
	}
	public  V cacheLoad(String cacheKey, K key,AbsBaseContainer<K,V> type){
	
		return cacheContext.cacheLoad(cacheKey, key, type);
	}
	public boolean cacheContainsKey(String cacheKey,K key,AbsBaseContainer<K,V> type){
		
		return cacheContext.cacheContainsKey(cacheKey, key, type);
	}
	public V cacheDelete(String cacheKey,K key,AbsBaseContainer<K,V> type){
		
		return cacheContext.cacheDelete(cacheKey, key, type);
	}
	/**注册缓存容器
	 * 
	 * @return true:已注册  ,false:注册失败
	 * 
	 * */
	public boolean registerCache(String cacheKey,AbsBaseContainer<K,V> type){
		return cacheContext.registerCache(cacheKey, type);
	}
	
}
