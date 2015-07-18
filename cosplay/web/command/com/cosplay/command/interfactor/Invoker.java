package com.cosplay.command.interfactor;


public interface Invoker extends Command{
	
	/**注册接收者*/
	public void registerReceiver(Receiver receive);
	

}
