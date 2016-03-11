package com.cosplay.cache.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cosplay.cache.config.CacheConfig;
import com.cosplay.cache.container.AbsBaseContainer;

/**
 * 缓存容器上下文
 * */
public class CacheContext<K,V> {
	
	private Map<String,Map<Integer,AbsBaseContainer<K,V>>> readCacheContainer =new HashMap<String,Map<Integer,AbsBaseContainer<K,V>>>();
	

	CacheContext(){
		init();
	}
	private void init(){
		List<String> cacheList =  CacheConfig.getCacheProList();
		for(String cacheKey:cacheList){
			Map<Integer,AbsBaseContainer<K,V>> cacheTypeContainer = new HashMap<Integer,AbsBaseContainer<K,V>>();
			
			readCacheContainer.put(cacheKey, cacheTypeContainer);
		}
	}
	/**注册缓存容器
	 * 
	 * @return true:已注册  ,false:注册失败
	 * 
	 * */
	boolean registerCache(String cacheKey,AbsBaseContainer<K,V> type){
		//如果缓存key都没注册
		if( !readCacheContainer.containsKey(cacheKey)){
			return false;
		}
		//如果缓存容器已注册
		if( readCacheContainer.get(cacheKey).containsKey(type.hashCode()) ){
			return true;
		}
		//注册
		readCacheContainer.get(cacheKey).put(type.hashCode(), type);
		
		return true;
	}
	void cacheInsert(String cacheKey, K key, V value,AbsBaseContainer<K,V> type) {
	/*	if( readCacheContainer.size()==0){
			return ;
		}
		if( !readCacheContainer.containsKey(cacheKey)){
			return ;
		}*/
		if(cacheKey==null){
			return ;
		}
		if(key ==null){
			return ;
		}
		readCacheContainer.get(cacheKey).get(type.hashCode()).cacheInsert(key, value);
	}

	V cacheLoad(String cacheKey, K key,AbsBaseContainer<K,V> type) {
		/*if( readCacheContainer.size()==0){
			return null;
		}
		if( !readCacheContainer.containsKey(cacheKey)){
			return null ;
		}
		*/
		if(cacheKey==null){
			return null;
		}
		if(key ==null){
			return null;
		}
		return readCacheContainer.get(cacheKey).get(type.hashCode()).cacheLoad(key);
	}
	boolean cacheContainsKey(String cacheKey,K key,AbsBaseContainer<K,V> type){
		if(cacheKey==null){
			return false;
		}
		if(key ==null){
			return false;
		}
		
		return readCacheContainer.get(cacheKey).get(type.hashCode()).containsKey(key);
	}
	
	V cacheDelete(String cacheKey, K key,AbsBaseContainer<K,V> type) {
		if(cacheKey==null){
			return null;
		}
		if(key ==null){
			return null;
		}
		 return	readCacheContainer.get(cacheKey).get(type.hashCode()).remove(key);
	}
}
