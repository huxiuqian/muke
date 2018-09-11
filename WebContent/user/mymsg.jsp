<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
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
	var user = $("sessionScope.user").val();
	function getMyMsg(){
		// ajax 异步获取我的问题		
		$.ajax({
			url : "UserMessageServlet",
			type : "POST",
			async : "true",
			data : {"action": "getMyMsg", "pageNum" : pageNum, "user": user},
			dataType : "json",
			success : function(data) {
					$.each(data.data.data, function(index, msgItem) {
						//alert("msgItem.msgtopic");
						var msg = $(".template").clone();
						msg.show();
						msg.removeClass("template");
						msg.find(".title").text(msgItem.msgtopic);
						msg.find(".title").attr("href", "message.jsp?msgid="+msgItem.msgid);
						msg.find(".time").text(msgItem.msgtime);
						msg.find(".count").text(msgItem.accessCount+" • "+msgItem.replyCount);
	
						$(".p").before(msg);
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
		getMyMsg();
	});

</script>
</head>
<body>
	<jsp:include flush="fasle" page="../header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 msgtitle"><h3>我的问题</h3></div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-xs-8"><h4>&nbsp;&nbsp;&nbsp;标题</h4></div>
			<div class="col-sm-2 col-xs-4 text-center"><h4>时间</h4></div>
			<div class="col-sm-2 hidden-xs text-center"><h4>浏览 • 回复</h4></div>
		</div>
		<div class="row msglist template" style="display: block;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit" >
				<a class="title"></a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center">
				<span class="time"></span>
				</div>
				<div class="col-sm-2 hidden-xs text-center">
				<span class="count"></span>
				</div>
			</div>
		</div>

		<div class="row p">
			<div class="col-sm-12">
				<br/>
				<button id="loadmore" type="button" class="btn btn-default btn-lg btn-block" 
				onclick="javascript:getMyMsg();">加载更多...</button>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="../footer.jsp" />
</body>
</html>