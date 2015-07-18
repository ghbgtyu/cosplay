package com.cosplay.command.test;

import com.cosplay.command.annotation.CommandAction;
import com.cosplay.command.annotation.CommandMapping;

@CommandAction
public class CommandTest2 {
	
	@CommandMapping(mapping = "2")
	public void call(Object obj){
		System.out.println("2--"+obj);
	}
}
