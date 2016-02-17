package com.cosplay.check.handler.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cosplay.bus.code.ErrorCode;
import com.cosplay.check.constants.CheckConstants;
import com.cosplay.check.handler.AbsCheckHandler;
import com.cosplay.check.model.NormalCheckResult;
/**检验用户昵称*/
public class UserNameCheckHandler extends AbsCheckHandler {
	
	private String name;
    private NormalCheckResult result ;

    public UserNameCheckHandler(NormalCheckResult result,String name) {
    	this.result = result;
    	this.name = name;
    }
    
	@Override
	public boolean check() {
		Pattern p = Pattern.compile(CheckConstants.MATCH_USER_NAME);
		Matcher m = p.matcher(name);
		boolean flag = m.matches();
		if(flag){
			this.result.setSuccess(true);
		}else{
			this.result.setErrorCode(ErrorCode.ERROR_1005);
			this.result.setSuccess(false);
		}
		return flag;
	}

}
