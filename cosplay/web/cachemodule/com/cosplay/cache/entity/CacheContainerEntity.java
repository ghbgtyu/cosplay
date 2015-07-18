package com.cosplay.cache.entity;

/**
 * 
 * 缓存容器
 * */
public class CacheContainerEntity {
	
	
	private CacheEntity cacheEntity;

	private CacheConfigEntity cacheConfigEntity;
	
	public CacheEntity getCacheEntity() {
		return cacheEntity;
	}

	public void setCacheEntity(CacheEntity cacheEntity) {
		this.cacheEntity = cacheEntity;
	}

	public CacheConfigEntity getCacheConfigEntity() {
		return cacheConfigEntity;
	}

	public void setCacheConfigEntity(CacheConfigEntity cacheConfigEntity) {
		this.cacheConfigEntity = cacheConfigEntity;
	}

}
