package com.cosplay.cache.export;

import com.cosplay.cache.container.AbsBaseContainer;





/**
 * 缓存存储接口
 * K : key 的类型
 * V ：value 的类型
 * T ：缓存类型
 * */

public interface ICacheExportService <K,V>{
	/**
	 * 存入缓存
	 * @param cacheKey :注册的key值
	 * @param key :存进缓存的key
	 * @param value :存进缓存的值
	 * */
	public void cacheInsert(K key,V value);
	
	
	/**
	 * 从缓存取值
	 * @param cacheKey :注册的key值
	 * @param key :存进缓存的key
	 * */
	public V cacheLoad(K key);
	
	/**查询缓存中是否有该key对应的value*/
	public boolean cacheContainsKey(K key);
}
