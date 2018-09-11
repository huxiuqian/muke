<%@page import="com.hpe.muke.util.Page"%>
<%@page import="com.hpe.muke.po.Message"%>
<%@page import="com.hpe.muke.dao.MessageDao"%>
<%@page import="com.hpe.muke.dao.impl.MessageDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
	
	var pageNum = 1;
	function getMsg(){
		// Ajax 获取最新的问题
		$.ajax({
			url : "MessageServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "new", "pageNum": pageNum },			
			dataType : "json",
			success : function(data) {
					$.each(data.data.data, function(index, msgItem) {
						var msg = $(".template").clone();
						msg.show();
						msg.removeClass("template");
						msg.find(".msg-title").text(msgItem.msgtopic);
						msg.find(".msg-title").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".author").text(msgItem.realname+" •  "+msgItem.msgtime);
						msg.find(".msgcontent").html(msgItem.msgcontents);
						msg.find(".count").text(msgItem.accessCount+"次浏览 • "+msgItem.replyCount+"个回复 • ");
						msg.find(".msglink").attr("href", "message.jsp?msgid="+msgItem.msgid);
						if (msgItem.state == 1){
							msg.find(".wonderful").text("精");
						}
						$("#msgList").append(msg);
					});
	
					pageNum++;
					// 加载更多
					if (parseInt(data.data.totalPage) >= parseInt(pageNum)){
						$("#loadmore").html("加载更多...");
					}
					else{
						$("#loadmore").html("没有更多数据了！");
					}
			}
		});
	}

	$(function(){
		// 加载数据
		getMsg();
	});

</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row" id="msgList">
			<div class="col-sm-12 msgitem template">
			<span class="label label-danger wonderful"></span>
				<h3><a class="msg-title"></a></h3>				
				<p class="author"></p>
				<p class="msgcontent"></p>
				<div class="rightinfo">
					<span class="count"></span>
					<a class="msglink">详细&gt;&gt;</a>
				</div>
			</div>
			<!-- 
			<div class="col-sm-12 msgitem" style="display: block;">
				<h3><a class="msg-title" href="#">JDK配置环境变量</a></h3>
				<p class="author">冰城小象 •  8/28</p>
				<p class="msgcontent">如题:</p>
				<div class="rightinfo">
					<span class="count">8次浏览 • 5个回复 • </span>
					<a class="msglink" href="#">详细&gt;&gt;</a>
				</div>
			</div> 
			-->
		</div>
		<div class="row">
			<div class="col-sm-12">
				<br/>
				<button id="loadmore" type="button" class="btn btn-default btn-lg btn-block" 
				onclick="javascript:getMsg();">加载更多...</button>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>