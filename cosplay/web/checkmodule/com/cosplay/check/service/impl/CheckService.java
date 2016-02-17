package com.cosplay.check.service.impl;

import org.springframework.stereotype.Service;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.service.ICheckService;

@Service
public class CheckService implements ICheckService {

	
	
	
	@Override
	public boolean check(AbsCheckHandler... handler) {
	
		boolean flag = true;
		for (AbsCheckHandler h : handler) {
			try {
				flag = h.check();
				if(!flag){//如果有一个检验失败了，那么下面的不检验了
					break;
				}
			} catch (Exception e) {
				CosplayLog.error("检验出错", e);
			}
		}
		return flag;
	}

	

}
