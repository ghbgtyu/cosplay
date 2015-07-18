package com.cosplay.cache.entity;

public class CacheConfigEntity {
	//缓存状态 （1：开启，0：关闭
	private Integer status =0; 
	//缓存清理上限
	private Integer clearCeilingSize =1000;
	//清理因子 (小时
	private Double clearPeriodic =5.0;
	
	private Integer clearActive = 5;
	
	private Integer cacheActive = 10;
	
	private Double risePeriodic = 1.5;
	
	
	
	
	public Integer getClearActive() {
		return clearActive;
	}
	public void setClearActive(Integer clearActive) {
		this.clearActive = clearActive;
	}
	public Integer getCacheActive() {
		return cacheActive;
	}
	public void setCacheActive(Integer cacheActive) {
		this.cacheActive = cacheActive;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public Integer getClearCeilingSize() {
		return clearCeilingSize;
	}
	public void setClearCeilingSize(Integer clearCeilingSize) {
		this.clearCeilingSize = clearCeilingSize;
	}
	public Double getClearPeriodic() {
		return clearPeriodic;
	}
	public void setClearPeriodic(Double clearPeriodic) {
		this.clearPeriodic = clearPeriodic;
	}
	public Double getRisePeriodic() {
		return risePeriodic;
	}
	public void setRisePeriodic(Double risePeriodic) {
		this.risePeriodic = risePeriodic;
	}
	
}
