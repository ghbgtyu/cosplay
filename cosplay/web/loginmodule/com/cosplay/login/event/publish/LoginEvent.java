package com.cosplay.login.event.publish;

import com.cosplay.bus.event.EventPublicType;
import com.cosplay.serviceserver.eventservice.model.IEvent;

/**登陆成功所抛事件*/
public class LoginEvent implements IEvent {

	private Object []data;
	public LoginEvent(Object userRoleId){
		data = new Object []{
				userRoleId
		};
	}
	
	@Override
	public String getType() {
		return EventPublicType.LOGIN_EVENT;
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
