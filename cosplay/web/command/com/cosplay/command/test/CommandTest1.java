package com.cosplay.command.test;

import org.springframework.stereotype.Component;

import com.cosplay.command.annotation.CommandAction;
import com.cosplay.command.annotation.CommandMapping;
@Component
@CommandAction
public class CommandTest1 {
	
	@CommandMapping(mapping = "1")
	public void call(Object o ){
		System.out.println("1--"+o);
	}
}
