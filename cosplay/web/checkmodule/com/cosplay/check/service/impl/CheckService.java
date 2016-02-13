package com.cosplay.check.service.impl;

import org.springframework.stereotype.Service;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.service.ICheckService;

@Service
public class CheckService implements ICheckService {

	
	
	
	@Override
	public void check(AbsCheckHandler... handler) {
	
		for (AbsCheckHandler h : handler) {
			try {
				h.check();
			} catch (Exception e) {
				CosplayLog.error("检验出错", e);
			}
		}
		
	}

	

}
