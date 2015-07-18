package type;

public enum SessionType {
	WEBSOCKET(1);
	
	private int type;
	
	private SessionType(int type){
		this.type = type;
	}
	public int getType() {
		return type;
	}
	
}
