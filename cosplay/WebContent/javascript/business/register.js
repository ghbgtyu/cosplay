
var Register ={};



/**
 * 检测用户名有没有被使用
 * */
Register.checkUserName = function(){
	//绑定失去焦点事件
	$("#userLoginName").blur(function(){
		
		var inputDom = document.getElementById("userLoginName").value;
		var errorDom = document.getElementById("userLoginNameError");
		//发送ajax请求验证
		jQuery.ajax({
			   type: "POST",
			   url: "/checkUserName?userLoginName="+inputDom,
			   success: function(msg){
			     if( msg.result == false ){
			    	 errorDom.innerHTML =ERRORCODE.getErrorMsg(msg.code);
			     }else{
			    	 errorDom.innerHTML = "ok";
			     }
			   },
			   complete: function (XHR, TS) { XHR = null }
			});
		

	});
};

/**
 * 检测用户昵称有没有被使用
 * */
Register.checkUserCosName = function(){
	//绑定失去焦点事件
	$("#userCosName").blur(function(){
		var inputDom = document.getElementById("userCosName").value;
		var errorDom = document.getElementById("userLoginCosNameError");
		//发送ajax请求验证
		jQuery.ajax({
			   type: "POST",
			   url: "/checkUserName?userCosName="+inputDom,
			   success: function(msg){
			     if( msg.result == false ){
			    	 errorDom.innerHTML =ERRORCODE.getErrorMsg(msg.code);
			     }else{
			    	 errorDom.innerHTML = "ok";
			     }
			   },
			   complete: function (XHR, TS) { XHR = null }
			});
	});
};
Register.register = function(){
	/*var inputDomLoginName = document.getElementById("userLoginName").value;
	var inputDomCosName = document.getElementById("userCosName").value;
	var inputDomPwd = document.getElementById("userPassWord").value;*/
	//发送ajax请求验证
	/*jQuery.ajax({
		   type: "POST",
		   url: "/register?userCosName="+inputDomCosName+"&userLoginName="+inputDomLoginName+"&userPassWord="+inputDomPwd,
		   success: function(msg){
		     if( msg.result == false ){
		    	 alert(ERRORCODE.getErrorMsg(msg.code));
		     }else{
		    	alert("ok");
		     }
		   },
		   async: true,
		   complete: function (XHR, TS) { XHR = null }
		});*/
	

    jQuery.ajax({
		cache : true,
		type : "POST",
		url : "/register",
		data : $('#register').serialize(),// 你的formid
		success : function(msg) {
			if (msg.result == false) {
				alert(ERRORCODE.getErrorMsg(msg.code));
			} else {
				alert("ok");
			}
		},
		async : true,
		complete : function(XHR, TS) {
			XHR = null
		}
	});
	
}