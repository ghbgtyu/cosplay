
var Register ={};


var userNameErrorInfo = "该用户名已被别人抱走";

var userCosNameErrorInfo = "该昵称已经被人抱走";
//ajax 返回json 
var ajaxResult = "result";



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
			    	 errorDom.innerHTML = userNameErrorInfo;
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
			    	 errorDom.innerHTML = userCosNameErrorInfo;
			     }else{
			    	 errorDom.innerHTML = "ok";
			     }
			   },
			   complete: function (XHR, TS) { XHR = null }
			});
	});
};
