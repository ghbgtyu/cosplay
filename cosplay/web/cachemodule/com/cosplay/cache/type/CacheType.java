package com.cosplay.cache.type;

import com.cosplay.cache.container.AbsBaseContainer;
import com.cosplay.cache.container.ReadOnlyCacheContainer;

/**
 * 缓存类型 
 * **/
public enum CacheType {
	READ(ReadOnlyCacheContainer.class);
	private final Class<?extends AbsBaseContainer> type;
	private CacheType(Class<?extends AbsBaseContainer> type){
		this.type = type;
	}
	public Class<?extends AbsBaseContainer> getType(){
		return this.type;
	}
	
}
