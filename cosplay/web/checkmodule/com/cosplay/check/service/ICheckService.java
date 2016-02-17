package com.cosplay.check.service;


import com.cosplay.check.handler.AbsCheckHandler;

public interface ICheckService {
	/**检验
	 * 
	 * @param handler 处理类
	 * */
	public boolean check(AbsCheckHandler ...handler);
}
