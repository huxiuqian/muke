<%@page import="com.hpe.muke.util.Page"%>
<%@page import="com.hpe.muke.po.Message"%>
<%@page import="com.hpe.muke.dao.MessageDao"%>

<%@page import="com.hpe.muke.dao.impl.MessageDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script language="javascript">

	function getNew() {		
		// Ajax异步请求最新五条
		$.ajax({
			url : "MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "new", "pageNum": "1" },			
			dataType : "json",
			success : function(data) {
					// 遍历数组内容
					$.each(data.data.data, function(index, msgItem) {
						// 根据HTML模版创建副本
						var msg1 = $(".template").clone();
						// 设置属性
						msg1.attr("display", "block");
						msg1.removeClass("template");
						msg1.find(".msgtile").text(msgItem.msgtopic);
						msg1.find(".msgtile").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg1.find(".badge").text(msgItem.msgtime);
						//if (msgItem.state == 2){
						//	msg3.find(".top").text("置顶");							
						//}else 
							if (msgItem.state == 1){
							msg1.find(".wonderful").text("精");
						}
						// 节点追加
						$(".newList").append(msg1);
					});
			}
		});		
	}

	
	function getHot() {		
		// Ajax异步请求最热五条,就是评论最多的五条
		var pageNum = "1";
		$.ajax({
			url : "MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "hot", "pageNum": "1" },			
			dataType : "json",
			success : function(data) {
					// 遍历数组内容
					$.each(data.data.data, function(index, msgItem) {
						// 根据HTML模版创建副本
						var msg2 = $(".template").clone();
						// 设置属性
						msg2.attr("display", "block");
						msg2.removeClass("template");
						msg2.find(".msgtile").text(msgItem.msgtopic);
						msg2.find(".msgtile").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg2.find(".badge").text(msgItem.replyCount);
						//if (msgItem.state == 2){
						//	msg3.find(".top").text("置顶");							
						//}else 
							if (msgItem.state == 1){
							msg2.find(".wonderful").text("精");
						}
						// 节点追加
						$(".hotList").append(msg2);
					});
			}
		});		
	}
	
	function getTheme() {	
		// Ajax异步请求, 最热的五个主题
		var pageNum = "1";
		$.ajax({
			url : "MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "theme", "pageNum": "1" },			
			dataType : "json",
			success : function(data) {
					// 遍历数组内容
					$.each(data.data.data, function(index, msgItem) {
						// 根据HTML模版创建副本
						var msg3 = $(".template").clone();
						// 设置属性
						msg3.attr("display", "block");
						msg3.removeClass("template");
						msg3.find(".msgtile").text(msgItem.msgtopic);
						msg3.find(".msgtile").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg3.find(".badge").text(msgItem.thename);
						//if (msgItem.state == 2){
						//	msg3.find(".top").text("置顶");							
						//}else
							if (msgItem.state == 1){
							msg3.find(".wonderful").text("精");
						}
						
						// 节点追加
						$(".themeList").append(msg3);
					});
			}
		});		
	}

	
	$(function() {
		getNew();
		getHot();
		getTheme();
	});
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">
		
		<div class="row">
			<div class="col-sm-4">
				<div
					style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
					<div style="float: left">
						<h3 style="display: inline">最新</h3>
					</div>
					<div style="float: right; vertical-align: bottom;">
						<a href="newmsg.jsp">更多>></a>
					</div>
				</div>
				<div>
					<ul class="list-group newList">
						<li class="list-group-item template">
							<span class="badge"></span>							
							<a class="msgtile" length="300px"></a>
							<span class="label label-danger wonderful"></span>
						</li>
					</ul>
				</div>
				
			</div>
			<div class="col-sm-4">
				<div
					style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
					<div style="float: left">
						<h3 style="display: inline">最热</h3>
					</div>
					<div style="float: right; vertical-align: bottom;">
						<a href="hotmsg.jsp">更多>></a>
					</div>
				</div>
				<div>
					<ul class="list-group hotList">

					</ul>
				</div>
			</div>
			<div class="col-sm-4">
				<div
					style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
					<div style="float: left">
						<h3 style="display: inline">话题</h3>
					</div>
					<div style="float: right; vertical-align: bottom;">
						<a href="thememsg.jsp">更多>></a>
					</div>
				</div>
				<div>
					<ul class="list-group themeList">
	
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
	
</body>
</html>