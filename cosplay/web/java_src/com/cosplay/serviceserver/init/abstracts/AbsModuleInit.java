package com.cosplay.serviceserver.init.abstracts;

import javax.annotation.PostConstruct;

import com.cosplay.serviceserver.eventservice.export.IEventServiceExportService;
import com.cosplay.serviceserver.eventservice.export.impl.EventServiceExportServiceImpl;
import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.orm.core.OrmConvertorFactory;

/**
 * 模块初始化抽象类
 * 
 * */
public abstract class AbsModuleInit {
	private IEventServiceExportService eventServiceExportService = new EventServiceExportServiceImpl();
	@PostConstruct
	public void init(){
		otherInit();
		//订阅事件
		IEventHandler[] eventHandlers = getEventHandlers();
		if(null != eventHandlers){
			for(IEventHandler handler : eventHandlers){
				eventServiceExportService.subscribe(handler.getEventType(),getOrder(), handler);
			}
		}
		System.out.println("-------------module init finish-------------"+getClass().getSimpleName()+"---order:"+getOrder());
	}
	/**
	 * 事件优先级
	 * */
	protected int getOrder(){
		return 200;
	}
	/**
	 * 注册所有订阅事件
	 */
	protected  IEventHandler[] getEventHandlers(){return null;};
	/**
	 * 子类可以重写这方法，进行初始化的一些工作
	 * */
	public abstract void otherInit();
}
