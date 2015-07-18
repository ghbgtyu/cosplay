package com.cosplay.login.event.publish;

import com.cosplay.bus.event.EventPublicType;
import com.cosplay.serviceserver.eventservice.model.IEvent;

/**登陆以后发布用户在线事件*/
public class LoginOnlineEvent implements IEvent {

	private Object []data;
	public LoginOnlineEvent(Object userRoleId){
		data = new Object []{
				userRoleId
		};
	}
	
	@Override
	public String getType() {
		return EventPublicType.LOGIN_ONLINE;
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
