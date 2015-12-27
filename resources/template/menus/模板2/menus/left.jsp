<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />	
<html>
<head>
	<title>系统主页-左侧页面</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/menuStatic/esayui/jquery-easyui-1.3.6/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/menuStatic/esayui/jquery-easyui-1.3.6/themes/icon.css"/>
	
	<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/menuStatic/esayui/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
</head>

<body>
<div class="row-fluid">
		<div class="span12" style="background-color:#ecf5fe">
			<div class="easyui-accordion" data-options="multiple:true,border:false" style="left:5px !important;">
				<c:forEach var="menu" items="${menus}" varStatus="status">
					<c:if test="${menu ne null}">
						<div title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${menu.moduleCnname}'/>" data-options="collapsed:true" style="padding:0px 10px 10px 10px;">
							<c:if test="${menu.resourceReg ne null }">
								<div id="collapse<c:out value='${status.index}'/>" class="accordion-body collapse in">
									<c:forEach var="resourceReg" items="${menu.resourceReg}">
										<ul class="nav nav-list" style="border-bottom:1px dashed #33a7df;">
											<li>
												<a class="MenuClick" id="<c:out value='${resourceReg.resUUID}'/>" curl="<c:url value='${resourceReg.resUrl }' />" target="default" style="cursor:pointer">
													<i class="icon-tasks"></i>&nbsp;&nbsp;<c:out value='${resourceReg.resCnname}'/>
												</a>
											</li>
										</ul>
									</c:forEach>
								</div>
							</c:if>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</body>

</html>
<script type="text/javascript">
	$(document).ready(function(){
		$("a").click(function(){
			if($(this).attr("class") == "accordion-toggle") return;
			$("li").removeClass("active");
			$(this).parent("li").addClass("active");
			return true;
		});
		//点击树事件
		$(".MenuClick").bind("click",function(){
			$(window.parent.document).find("#main-center").attr("src",$(this).attr("curl"));
		});
		//改变accordion控制图片位置
		$(".row-fluid").find(".panel-tool").css("left","5px").css("width","16px");
	});
	
	function changeIcon(icon){
		var obj = $("#"+icon);
		if("icon-chevron-right"==obj.attr("class")){
			obj.attr("class","icon-chevron-down");
		}else if("icon-chevron-down"==obj.attr("class")){
			obj.attr("class","icon-chevron-right");
		}
		var navList = $("#collapse"+icon.substr(1,icon.length));
		navList.animate({height: 'toggle', opacity: 'toggle'}, "fast");
	}
</script>