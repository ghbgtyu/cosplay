package com.cosplay.serviceserver.eventservice.model;

/**
 * @description 
 * @date 2013-2-21 上午11:24:01 
 */
public interface IEvent {
	
	/**
	 * 事件标识
	 */
	public String getType();
	
	/**
	 * 事件数据
	 */
	public Object getData();
	
	/**
	 * 事件来源(角色事件一般返回userRoleId)
	 */
	public Object getRource();

}
