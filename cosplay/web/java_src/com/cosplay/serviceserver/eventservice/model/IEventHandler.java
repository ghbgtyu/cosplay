package com.cosplay.serviceserver.eventservice.model;

public interface IEventHandler {
	
	/**
	 * 事件处理
	 * @param source 事件来源
	 * @param eventData 事件数据
	 */
	public void handle(Object source,Object eventData);
	
	/**
	 * 获取事件类型
	 */
	public String getEventType();

}
