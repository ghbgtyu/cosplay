package repertory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.cosplay.base.util.IdUtil;

import interfaces.ISession;
import interfaces.ISessionControl;

/**客户端连接的仓库*/
@Service
public class ClientSessionManager implements ISessionControl{

	private Map<String, ISession>sessionMap = new ConcurrentHashMap<String, ISession>();
	
	@Override
	public String register(ISession session) {
		String sid = IdUtil.nextString("session");
		sessionMap.put(sid, session);
		return sid;
	}

	@Override
	public ISession getSessionBySid(String sid) {
		return sessionMap.get(sid);
	}
	
}
