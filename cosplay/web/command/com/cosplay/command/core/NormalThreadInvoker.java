package com.cosplay.command.core;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.command.constants.CommandConstants;
import com.cosplay.command.entity.MessageEntity;
import com.cosplay.command.interfactor.Invoker;
import com.cosplay.command.interfactor.Receiver;
import com.cosplay.thread.export.IThreadExportService;

@Component(value=CommandConstants.THREAD_INVOKER)
public class NormalThreadInvoker implements Invoker {
	@Autowired
	private Receiver receive ;
	
	private String threadConfigKey;
	
	@Autowired
	private IThreadExportService threadExportService;
	
	@Override
	public void registerReceiver(Receiver receive) {
		this.receive = receive;
	}

	@Override
	public void execute(MessageEntity msg) {  
		threadExportService.getExecutorServiceByKey(threadConfigKey).execute(createRunnable(msg));
	}
	public NormalThreadInvoker registerThreadKey(String key){
		this.threadConfigKey = key;
		return this;
	}
	Runnable createRunnable(MessageEntity msg){
		return new RunnableImpl(msg);
	}
	private class RunnableImpl implements Runnable{
		private MessageEntity msg;
		
		private RunnableImpl( MessageEntity msg){
			this.msg = msg;
		}
		
		@Override
		public void run() {
			receive.execute(msg);
		}
	}

}
