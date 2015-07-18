package com.cosplay.command.factory;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cosplay.command.constants.CommandConstants;
import com.cosplay.command.core.NormalThreadInvoker;
import com.cosplay.command.interfactor.Invoker;
import com.cosplay.thread.ThreadKey;

@Component
public class CommandFactory {
	
	@Resource(name=CommandConstants.DEFAULT_INVOKER)
	private Invoker defaultInvoker;
	@Resource(name=CommandConstants.THREAD_INVOKER)
	private Invoker threadInvoker;
	
	
	/**
	 * 获取默认请求者
	 * 单线程
	 * */
	public  Invoker getDefaultInvoker(){
		return getInvoker(CommandConstants.DEFAULT_INVOKER);
	}
	/**获取多线程请求者
	 * 
	 * @param threadKey 线程配置文件key
	 * */
	public  Invoker getThreadInvoker(String threadKey){
		return ((NormalThreadInvoker)getInvoker(CommandConstants.THREAD_INVOKER)).registerThreadKey(threadKey);
	}
	
	private  Invoker getInvoker(String type){
		switch(type){
			case CommandConstants.DEFAULT_INVOKER:return defaultInvoker;
			case CommandConstants.THREAD_INVOKER:return threadInvoker;
			default :return defaultInvoker;
		}
	}
	public ThreadKey getThreadKey(){
		return ThreadKey.getInstance();
	}
 }
