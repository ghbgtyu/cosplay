package com.cosplay.serviceserver.eventservice.model.impl;

import java.util.HashMap;
import java.util.Map;

import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;



/**
 * @description 事件处理中心
 * @date 2013-3-6 上午1:14:52 
 */

public class EventCenter {
	
	/**
	 * key:事件类型编号
	 * val:事件类型对象
	 */
	private Map<String,EventType> eventTypeMap = new HashMap<String, EventType>();

	public void subscribe(String eventType, int handleOrder, IEventHandler eventHandler) {
		
		synchronized(eventTypeMap){
			
			EventType _eventType = eventTypeMap.get(eventType);
			if(null == _eventType){
				_eventType = new EventType(eventType);
				eventTypeMap.put(eventType, _eventType);
			}
			
			_eventType.subscribe(handleOrder,eventHandler);
				
		}
		
	}

	public void publish(IEvent event) {
		
//		synchronized(eventTypeMap){
			
			EventType eventType = eventTypeMap.get(event.getType());
			if(null != eventType){
				eventType.publish(event);
			}
			
//		}
		
	}
	
	

}
