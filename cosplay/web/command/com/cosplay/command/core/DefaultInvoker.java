package com.cosplay.command.core;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosplay.command.constants.CommandConstants;
import com.cosplay.command.entity.MessageEntity;
import com.cosplay.command.interfactor.Invoker;
import com.cosplay.command.interfactor.Receiver;

/**默认请求者*/
@Component(value=CommandConstants.DEFAULT_INVOKER)
public class DefaultInvoker implements Invoker{
	
	@Autowired
	private Receiver receive ;

	@Override
	public void registerReceiver(Receiver receive) {
		this.receive = receive;
	}

	@Override
	public void execute(MessageEntity msg) {
		receive.execute(msg);
	}
	
}
