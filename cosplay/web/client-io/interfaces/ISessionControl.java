package interfaces;
/**session控制接口*/
public interface ISessionControl {
	/**注册session
	 * 
	 * @return sid
	 * */
	public String register(ISession session);
	/**根据id获取相应session*/
	public ISession getSessionBySid(String sid);
	
}
