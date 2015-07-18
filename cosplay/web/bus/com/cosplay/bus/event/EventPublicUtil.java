package com.cosplay.bus.event;

import com.cosplay.serviceserver.eventservice.export.IEventServiceExportService;
import com.cosplay.serviceserver.eventservice.export.impl.EventServiceExportServiceImpl;
import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;

public class EventPublicUtil  {
	private static IEventServiceExportService eventServiceExportService = new EventServiceExportServiceImpl();
	

	public static void subscribe(String eventType, int handleOrder,IEventHandler handler) {
		eventServiceExportService.subscribe(eventType, handleOrder, handler);
	}

	
	public static void publish(IEvent event) {
		eventServiceExportService.publish(event);
	}

}
