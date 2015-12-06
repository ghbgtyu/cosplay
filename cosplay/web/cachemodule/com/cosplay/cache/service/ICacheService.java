package com.cosplay.cache.service;

import com.cosplay.cache.container.AbsBaseContainer;



/**
 * 缓存存储接口
 * K : key 的类型
 * V ：value 的类型
 * T ：缓存类型
 * */
public interface ICacheService <K,V,T extends AbsBaseContainer<K,V>>{
	/**
	 * 存入缓存
	 * @param cacheKey :注册的key值
	 * @param key :存进缓存的key
	 * @param value :存进缓存的值
	 * */
	public void cacheInsert(String cacheKey,K key,V value,T type);
	
	
	/**
	 * 从缓存取值
	 * @param cacheKey :注册的key值
	 * @param key :存进缓存的key
	 * */
	public V cacheLoad(String cacheKey,K key,T type);
	
	
	/**注册缓存容器
	 * 
	 * @return true:已注册  ,false:注册失败
	 * 
	 * */
	boolean checkCacheKey(String cacheKey, T type);

	/**查询缓存中是否有该key对应的value*/
	boolean cacheContainsKey(String cacheKey, K key, AbsBaseContainer<K, V> type);


	public V cacheDelete(String cacheKey, K key, T type);
}
