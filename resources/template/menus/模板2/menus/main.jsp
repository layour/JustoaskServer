<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />	
<html>
<head>
	<title>系统主页</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/menuStatic/esayui/jquery-easyui-1.3.6/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/menuStatic/esayui/jquery-easyui-1.3.6/themes/icon.css"/>
	
	<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/menuStatic/esayui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
	
</head>
<body class="easyui-layout">
			<!-- top -->
			<div id="main-top" data-options="region:'north'" style="width:100%;height:55%;overflow:hidden;">
			
			</div>
			<!-- left -->
			<div id="main-left" data-options="region:'west',split:true" title="<a onclick='homeClick();' style='text-decoration:none;cursor:pointer;' target='default'>&nbsp;<i class='icon-home'></i>&nbsp;&nbsp;首&nbsp;页</a>" style="width:220px;overflow:hidden;overflow-y:auto;">
			
			</div>
			<!-- center -->
			<div data-options="region:'center'" style="width:100%;overflow:hidden;">
				<iframe frameborder="no" border="0"  id="main-center" src=""  style="width:100%;height:100%;"></iframe>
			</div>
		
		<script type="text/javascript">
			$(document).ready(function(){
				//$("#easyui-layout-div").height(window.screen.availHeight);
				$("#main-top").load("<c:url value='/toTop' />");
				$("#main-left").load("<c:url value='/toLeft'/>");
				$("#main-left").panel('panel').resizable('disable');
				
			});
		
			function homeClick(){
				$("#main-center").attr("src","<c:url value='/static/menu/home.html' />");
			};
		</script>
	</body>
</html>
