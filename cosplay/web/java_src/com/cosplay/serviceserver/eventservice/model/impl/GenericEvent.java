/**
 * 
 */
package com.cosplay.serviceserver.eventservice.model.impl;

import com.cosplay.serviceserver.eventservice.model.IEvent;

/**
 * 通用事件
 */
public class GenericEvent implements IEvent{

	private String type;
	private Object []data;
	public GenericEvent(String type,Object[] data){
		this.type = type;
		this.data = data;
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public Object getRource() {
		return data[0];
	}

}
