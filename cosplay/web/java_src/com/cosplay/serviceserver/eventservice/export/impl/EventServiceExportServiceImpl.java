package com.cosplay.serviceserver.eventservice.export.impl;

import com.cosplay.serviceserver.eventservice.export.IEventServiceExportService;
import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.eventservice.service.IEventService;
import com.cosplay.serviceserver.eventservice.service.impl.EventServiceImpl;




public class EventServiceExportServiceImpl implements IEventServiceExportService {

	private IEventService eventService = new EventServiceImpl();
	
	@Override
	public void subscribe(String eventType,int handleOrder,
			IEventHandler eventHandler) {
		eventService.subscribe(eventType,handleOrder, eventHandler);
	}

	@Override
	public void publish(IEvent event) {
		eventService.publish(event);
	}

}
