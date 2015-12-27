<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>登录页面</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/loginStatic/css/login.css"/>
	<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
	
	
</head>

<body>
	<div id="container" >
		<form name="loginForm" id="loginForm">
			<div class="login"><img src="${ctx}/loginStatic/images/logo.png">&nbsp;Rcloud&nbsp;应用系统</div>
			<div class="username-text">用户名:</div>
			<div class="password-text">密码:</div>
			<div class="username-field">
				<input type="text" id="loginName" name="loginName" value="" />
			</div>
			<div class="password-field">
				<input type="password" id="loginPwd" name="loginPwd" value="" />
			</div>
				<input type="button" name="submit" value="&nbsp;登&nbsp;录 "  onclick="login();"/>
	            <input type="reset" name="reset" value="&nbsp;重&nbsp;置 " />
		</form>
	</div>
	<div id="footer">
		版权所有 © 2000-2014 中软国际公司
	</div>
	
	<script type="text/javascript">
	$(function(){
		$("#loginName").focus();
		//初始化回车键（Enter）事件，按下Enter键，执行登录方法
	    $(document).keydown(function(event){
	    	var keycode = event.which;
		    if (keycode == 13) {
		    	login();
		    }
	    });
	});

	//登录方法
	function login(){
		var url="${ctx}/login";
		$.getJSON(url, $("#loginForm").serialize() , function(result) {
			//if (result.code == "1") {//用户名密码验证成功，跳转到主页面
				window.location.href = "${ctx}/index";
				/*
			}else{//登录失败
				alert(result.msg);//提示失败信息
				if (result.code == "2") {//如果是登录账号为空，把焦点设置到账号输入框
					$("#loginName").focus();
				}else if (result.code == "3") {//如果是登录密码为空，把焦点设置到密码输入框
					$("#loginPwd").focus();
				}
			}
				*/
		});
		
	}
</script>
</body>
</html>
