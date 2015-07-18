package com.cosplay.user.moduleinit;

import org.springframework.stereotype.Component;

import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.init.abstracts.AbsModuleInit;

@Component
public class UserModuleInit extends AbsModuleInit {

	@Override
	protected int getOrder() {
		return 1;
	}

	@Override
	protected IEventHandler[] getEventHandlers() {
		return super.getEventHandlers();
	}
	
}
