package interfaces;

import java.io.IOException;

import bean.IoData;


/**
 * 
 * */
public interface ISession {

	/**发送消息给客户端*/
	public void sendClientMsg(IoData data) throws Exception;
	/**接收客户端的消息*/
	public void receiveClientMsg(IoData data)throws Exception;
	/**链接是否还存活着*/
	public boolean isOpen();
	/**关闭链接*/
	public void close() throws IOException;
}
