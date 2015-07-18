package com.cosplay.serviceserver.eventservice.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.cosplay.serviceserver.eventservice.model.IEvent;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;



public class EventType {
	
	private String eventType;
	
	private List<HandlerNode> eventHandlers = new ArrayList<HandlerNode>();
	
	
	public EventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * 订阅事件
	 * @param handleOrder 事件处理优先顺序，值越小优先级越高
	 */
	public void subscribe(int handleOrder, IEventHandler eventHandler){
		
		synchronized (this) {
			
			eventHandlers.add(new HandlerNode(eventHandler, handleOrder));
			Collections.sort(eventHandlers, new Comparator<HandlerNode>() {

				@Override
				public int compare(HandlerNode o1, HandlerNode o2) {
					
					if(o2.getHandleOrder() < o1.getHandleOrder()){
						return 1;
					}else if(o2.getHandleOrder() >= o1.getHandleOrder()){
						return -1;
					}
					
					return 0;
				}
				
			});
		}
		
	}
	
	public void publish(IEvent event){
		
		synchronized (this) {
			
			Iterator<HandlerNode> iterator = eventHandlers.iterator();
			
			while(iterator.hasNext()){
				try{
					IEventHandler handler = iterator.next().getEventHandler();
					handler.handle(event.getRource(),event.getData());
				}catch(Exception e){
					e.printStackTrace();
					/*if( event.getType().equals(EventPublishType.ROLE_LOGOUT) ){
						XianlingLog.offlineError(event.getRource().toString(), e);
					}else{
						XianlingLog.frameError("eventType:{},handler:{} handle error! {}", eventType, IEventHandler.class.getName(), e.getMessage());
					}*/
				}
			}
		}
		
	}
	
	private class HandlerNode{
		
		private IEventHandler eventHandler;
		private int handleOrder;
		
		public HandlerNode(IEventHandler eventHandler,int handleOrder) {
			this.handleOrder = handleOrder;
			this.eventHandler = eventHandler;
		}

		IEventHandler getEventHandler() {
			return eventHandler;
		}

		int getHandleOrder() {
			return handleOrder;
		}

	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				if(o2 < o1){
					return 1;
				}else if(o2 >= o1){
					return -1;
				}
				
				return 0;
			}
			
		});
		
		for(Integer tmp : list){
			System.out.println(tmp);
		}
		
	}
	
}
