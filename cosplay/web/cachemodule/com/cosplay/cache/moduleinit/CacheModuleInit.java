package com.cosplay.cache.moduleinit;

import com.cosplay.serviceserver.eventservice.model.IEventHandler;
import com.cosplay.serviceserver.init.abstracts.AbsModuleInit;
/**
 * 缓存模块初始化
 * */
public class CacheModuleInit extends AbsModuleInit{
	@Override
	protected int getOrder() {
		return 1;
	}

	@Override
	protected IEventHandler[] getEventHandlers() {
		return super.getEventHandlers();
	}

	@Override
	public void otherInit() {
		
	}
}
