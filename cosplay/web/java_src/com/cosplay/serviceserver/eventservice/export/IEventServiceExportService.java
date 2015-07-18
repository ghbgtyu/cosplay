package com.cosplay.serviceserver.eventservice.export;

import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;


public interface IEventServiceExportService {
	
	/**
	 * @param eventType 事件类型
	 * @param handleOrder 事件处理优先顺序，值越小优先级越高
	 * @param handler 事件处理器
	 */
	public void subscribe(String eventType, int handleOrder, IEventHandler handler);
	
	/**
	 * 发布事件
	 */
	public void publish(IEvent event);

}
