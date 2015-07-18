package com.cosplay.user.event.publish;

import com.cosplay.bus.event.EventPublicType;
import com.cosplay.serviceserver.eventservice.model.IEvent;

/**注册成功所抛事件*/
public class RegisterEvent implements IEvent {

	private Object []data;
	
	public RegisterEvent(String userCosName){
		data = new Object[]{
				userCosName
		};
	}
	
	@Override
	public String getType() {
		return EventPublicType.REGISTER_EVENT;
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
