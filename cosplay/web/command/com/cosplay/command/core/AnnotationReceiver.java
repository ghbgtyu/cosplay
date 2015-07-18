package com.cosplay.command.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cosplay.bus.log.CosplayLog;
import com.cosplay.command.annotation.CommandMapping;
import com.cosplay.command.constants.CommandConstants;
import com.cosplay.command.entity.MessageEntity;
import com.cosplay.command.entity.ReceiverEntity;
import com.cosplay.command.interfactor.Receiver;
import com.cosplay.command.template.CommandActionTemplate;

/**执行者集合*/
@Component
public class AnnotationReceiver implements Receiver{

	private Map<String,ReceiverEntity>receiveMap = new HashMap<String,ReceiverEntity>();
	//在所有包里找注册了commandAction的类
	private static CommandActionTemplate commandContainer = new CommandActionTemplate(CommandConstants.COMMAND_PACKAGE,Object.class);
		
	public AnnotationReceiver(){
		try {
			init();
		} catch (InstantiationException | IllegalAccessException e) {
			CosplayLog.error("AnnotationReceiver  初始化失败 "+e);
		}
	}
	
	private void init() throws InstantiationException, IllegalAccessException {
		List<Class<?>>classList = commandContainer.getClassList();
		for(Class<?> cl :classList){
			for(Method method:cl.getMethods()){
				CommandMapping mapping = method.getAnnotation(CommandMapping.class);
				if( mapping ==null ){
					continue;
				}
				if( receiveMap.containsKey(mapping.mapping() )){
					CosplayLog.error("命令有冲突，请检查 !  cmd :" +mapping.mapping()+"---class :"+cl.getName());
					throw new RuntimeException();
				}
				ReceiverEntity receiveBean = new ReceiverEntity();
				receiveBean.setTarget(cl.newInstance());
				receiveBean.setCmd(mapping.mapping());
				receiveBean.setMethod(method);
				receiveMap.put(receiveBean.getCmd(), receiveBean);
			}
		}
		
	}

	@Override
	public void execute(MessageEntity msg) {
		try {
			if( !receiveMap.containsKey(msg.getCmd()) ){
				return;
			}
			receiveMap.get(msg.getCmd()).getMethod().invoke(receiveMap.get(msg.getCmd()).getTarget(),msg.getData());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			CosplayLog.error("command 执行异常"+"---cmd:"+msg.getCmd()+"---data:"+msg.getData(), e);
		}
		
	}

}
