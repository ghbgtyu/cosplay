package com.cosplay.command.interfactor;

import com.cosplay.command.entity.MessageEntity;

public interface Command {
	/**开始执行*/
	public void execute(MessageEntity msg);
}
