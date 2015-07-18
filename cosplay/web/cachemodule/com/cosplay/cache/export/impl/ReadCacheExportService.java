package com.cosplay.cache.export.impl;






import com.cosplay.base.util.StringUtil;
import com.cosplay.cache.constants.CacheConstants;
import com.cosplay.cache.container.ReadOnlyCacheContainer;
import com.cosplay.cache.export.ICacheExportService;
import com.cosplay.cache.service.impl.BaseCacheService;



public class ReadCacheExportService<K,V> extends BaseCacheService<K, V, ReadOnlyCacheContainer<K,V>>implements ICacheExportService<K,V>{

	private String cacheKey =CacheConstants.DEFAULT_CACHE_CONFIG;
	
	private ReadOnlyCacheContainer<K,V> readOnlyCacheContainer ;
	
	
	private boolean registerState = false;
	
	public ReadCacheExportService(){};
	
	public ReadCacheExportService(String cacheKey){
		if ( !StringUtil.strIsEmpty(cacheKey) ){
			this.cacheKey = cacheKey;
			
		}
		readOnlyCacheContainer = new ReadOnlyCacheContainer<K,V>(cacheKey) ;
		//检验是否有注册缓存key
		if( super.checkCacheKey(cacheKey, readOnlyCacheContainer)){
			registerState = true;
		}
				
	}
	@Override
	public void cacheInsert(K key, V value) {
		if( !registerState ){
			return;
		}
		super.cacheInsert(cacheKey, key, value,readOnlyCacheContainer);
	}

	@Override
	public V cacheLoad(K key) {
		if( !registerState ){
			return null;
		}
		return super.cacheLoad(cacheKey, key, readOnlyCacheContainer);
	}
	@Override
	public boolean cacheContainsKey(K key){
		if( !registerState ){
			return false;
		}
		return super.cacheContainsKey(cacheKey, key, readOnlyCacheContainer);
	}
	
}
