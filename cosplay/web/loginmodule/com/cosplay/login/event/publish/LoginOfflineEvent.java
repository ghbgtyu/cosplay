package com.cosplay.login.event.publish;

import com.cosplay.bus.event.EventPublicType;
import com.cosplay.serviceserver.eventservice.model.IEvent;

/**下线以后发布用户下线事件*/
public class LoginOfflineEvent implements IEvent {

	private Object []data;
	public LoginOfflineEvent(Object userRoleId){
		data = new Object []{
				userRoleId
		};
	}
	
	@Override
	public String getType() {
		return EventPublicType.LOGIN_OFFLINE;
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
