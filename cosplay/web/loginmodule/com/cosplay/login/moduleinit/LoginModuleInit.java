package com.cosplay.login.moduleinit;

import org.springframework.stereotype.Component;

import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.init.abstracts.AbsModuleInit;
@Component
public class LoginModuleInit extends AbsModuleInit {
	
	@Override
	protected IEventHandler[] getEventHandlers() {
		return super.getEventHandlers();
	}
}
