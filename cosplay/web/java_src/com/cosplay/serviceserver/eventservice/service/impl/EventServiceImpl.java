package com.cosplay.serviceserver.eventservice.service.impl;

import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.eventservice.model.impl.EventCenter;
import com.cosplay.serviceserver.eventservice.service.IEventService;

public class EventServiceImpl implements IEventService{

	private EventCenter eventCenter = new EventCenter();
	
	@Override
	public void subscribe(String eventType, int handleOrder,IEventHandler eventHandler) {
		eventCenter.subscribe(eventType,handleOrder,eventHandler);
	}

	@Override
	public void publish(IEvent event) {
		eventCenter.publish(event);
	}
}
