package com.cosplay.check.handler.impl;


import javax.servlet.http.HttpServletRequest;

import com.cosplay.bus.code.ErrorCode;
import com.cosplay.check.geetest.GeetestConfig;
import com.cosplay.check.geetest.GeetestLib;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.model.NormalCheckResult;



/**验证码检验*/
public class GeetestCheckHandler extends AbsCheckHandler {
	
	private HttpServletRequest request;
	private NormalCheckResult result ;
	
	public GeetestCheckHandler(NormalCheckResult result,HttpServletRequest request){
		this.request = request;
		this.result = result;
	}

	@Override
	public boolean check() {
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
		
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		
		Object code = request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		if(code==null){
			return false;
		}
		//从session中获取gt-server状态
		int gt_server_status_code = (Integer) code;
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
				
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode);
		} else {
			// gt-server非正常情况下，进行failback模式验证
				
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
		}


		if (gtResult == 1) {
			// 验证成功
			this.result.setSuccess(true);
			return true;
		}
		else {
			// 验证失败
			this.result.setErrorCode(ErrorCode.ERROR_1007);
			this.result.setSuccess(false);
			return false;
		}
	}

}
