package com.cosplay.cache.entity;


public class CacheEntity<V> {
	/**
	 * 源数据
	 * */
	private V data;
	/**
	 * 活动时间戳
	 * */
	private long activeTime;
	
	
	public V getData() {
		return data;
	}
	public void setData(V data) {
		this.data = data;
	}
	public long getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(long activeTime) {
		this.activeTime = activeTime;
	}
	
	
}
