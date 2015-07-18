package bean;
/**通信对象*/
public class IoData {
	private String cmd;
	
	private String data;
	/**会话id，唯一*/
	private String sid;

	public String getCmd() {
		return cmd;
	}



	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}



	public String getSid() {
		return sid;
	}



	public void setSid(String sid) {
		this.sid = sid;
	}

	
}
