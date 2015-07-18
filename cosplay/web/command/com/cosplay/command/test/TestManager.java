package com.cosplay.command.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cosplay.command.entity.MessageEntity;
import com.cosplay.command.factory.CommandFactory;
@Component
public class TestManager {
	@Autowired
	private  CommandFactory commmandFactory;
	
	public void test(){
		while(true){

			for (int i = 0; i < 500; i++) {
				MessageEntity msg = new MessageEntity();
				msg.setCmd("1");
				msg.setData(i);
				MessageEntity msg1 = new MessageEntity();
				msg1.setCmd("2");
				msg1.setData(i);
				commmandFactory.getThreadInvoker(
						commmandFactory.getThreadKey().WEBSOCKET_KEY).execute(
						msg1);
				commmandFactory.getThreadInvoker(
						commmandFactory.getThreadKey().WEBSOCKET_KEY)
						.execute(msg);
			}
		}
	}
	
	public static void main(String[]args){
		BeanFactory bean = new ClassPathXmlApplicationContext("spring/spring-servlet.xml");
		TestManager t = bean.getBean(TestManager.class);
		t.test();
	}	
}
