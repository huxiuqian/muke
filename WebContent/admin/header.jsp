<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>头部</title>
<script type="text/javascript">
	function logout(){
		// Ajax 异步请求退出登录
		var admin = $("sessionScope.admin").val();
		$.ajax({
			url : "AdminServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "exit","admin" : admin },
			dataType : "json",
			success : function(data) {
				if (data.res == 0){
					window.location.replace("admin/login.jsp");
				}
			}
		});
		
		return false;
	}
</script>

</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
	<div class="container-fluid"> 
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
			<span class="sr-only">切换导航</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="admin/index.jsp">慕课答疑平台</a>
	</div>
	<div class="collapse navbar-collapse" id="example-navbar-collapse">
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${sessionScope.admin == null}">	
				<li><a href="admin/login.jsp">登录</a></li>			
			</c:if>
			<c:if test="${sessionScope.admin != null}">
			<li><a href="admin/msgmanager.jsp">帖子管理</a></li>
			<li><a href="admin/usermanager.jsp">用户管理</a></li>
			<li><a href="admin/thememanager.jsp">主题管理</a></li>
			<li class="dropdown">
			    	<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			        	${sessionScope.admin.name}<span class="caret"></span>
			    	</a>
			    	<ul class="dropdown-menu">
						<li><a href="admin/modifypw.jsp">修改密码</a></li>
						<li><a href="javascript:logout()">退出</a></li>
			      	</ul>
				</li>
			</c:if>
		</ul>
	</div>
	</div>
	</div>
</nav>
	<div class="jumbotron masthead">
		<div class="container">
			<h1>慕课答疑平台-后台</h1>
		</div>
	</div>
</body>
</html>