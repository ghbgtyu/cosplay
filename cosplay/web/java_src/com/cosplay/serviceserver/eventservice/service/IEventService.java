package com.cosplay.serviceserver.eventservice.service;

import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;

/**
 * @description 事件服务
 */
public interface IEventService {
	
	/**
	 * 订阅事件
	 * @param eventType 事件类型
	 * @param handleOrder 事件处理优先顺序，值越小优先级越高
	 * @param eventHandler 事件处理器
	 */
	public void subscribe(String eventType,int handleOrder, IEventHandler eventHandler);
	
	/**
	 * 发布事件
	 */
	public void publish(IEvent event);

}
