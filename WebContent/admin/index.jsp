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
$(function (){
	getMsgCount();
	getReplyCount();
});

function getMsgCount(){
	$.ajax({
		url : "AdminMessageServlet",
		type : "post",
		data : {"action" : "getMsgCount"},
		dataType : "json",
		success : function(data){
				$("#daymsg").append(data.day);
				$("#weekmsg").append(data.week);
				$("#monthmsg").append(data.month);
			}
	});
}
function getReplyCount(){
	$.ajax({
		url : "AdminMessageServlet",
		type : "post",
		data : {"action" : "getReplyCount"},
		dataType : "json",
		success : function(data){
				$("#dayrep").append(data.day);
				$("#weekrep").append(data.week);
				$("#monthrep").append(data.month);
		}
	});
}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 text-center">
				<h3>数据统计</h3>
				<table class="table table-striped">
				  <caption>发帖统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
						<tr>
							<td>今日发帖</td>
							<td id="daymsg"></td>
						</tr>
						<tr>
							<td>一周内发帖</td>
							<td id="weekmsg"></td>
						</tr>
						<tr>
							<td>一月内发帖</td>
							<td id="monthmsg"></td>
						</tr>
					</tbody>
				</table>
				<table class="table table-striped">
				  <caption>回复统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
						<tr>
							<td>今日回复</td>
							<td id="dayrep"></td>
						</tr>
						<tr>
							<td>一周内回复</td>
							<td id="weekrep"></td>
						</tr>
						<tr>
							<td>一月内回复</td>
							<td id="monthrep"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>