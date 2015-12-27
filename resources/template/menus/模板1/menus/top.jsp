<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />	
<html>
<head>
	<title>系统主页-顶部页面</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/menuStatic/css/main.css"/>
	
</head>

<body>
	<div class="row-fluid logo-wrapper">
		<div class="span12 logoarea">
			<div class="span6">
				<div class="logo"></div>
				<span class="logo-title">RCloud 应用系统</span>
			</div>
			<div class="span6" style="font-size: 14px">
				<small>
					<div style="height:25px;">
						<div class="pull-right-top">
							&nbsp;&nbsp;&nbsp;
						</div>
						<div class="pull-right-top">
							<a href="<c:url value='/logout' />" target="_top">
								<span class="small" style="color: #fff">
									<i class="icon-off icon-white"></i>&nbsp;退出系统&nbsp;&nbsp;
								</span>
							</a>
						</div>
						<div class="pull-right-top ">
							<i class="icon-user icon-white"></i> 欢迎您：<a href="javascript:top.location.href = \'#\'"><font color="#FFFFFF">系统管理员</font></a>&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</small>
			</div>
		</div>
	</div>


</body>
</html>
