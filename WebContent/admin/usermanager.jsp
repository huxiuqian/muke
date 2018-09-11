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
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="page/pagetool.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
	var pageNum = 1;//当前页面的页码
	var name = "";
	$(function() {
		getUser(pageNum);

	});
	function getUser(pageNum) { // ajax 异步请求 获取个人信息 
		$.ajax({
			url : "AdminUserServlet",
			data : {
				'action' : "getuser",
				'pagenum' : pageNum,
				'username' : name
			},
			dataType : 'json',
			type : 'post',
			success : function(date) {
				var date2 = eval(date);
				var num = 1;
				$(".list").html("");
				$.each(date2.data, function(index, userItem) {
					var user = $(".template").clone();
					user.show();
					user.removeClass("template");
					user.find(".num").text(index + 1);
					user.find(".name").text(userItem.username);
					user.find(".realname").text(userItem.realname);
					user.find(".name").text(userItem.username);
					user.find(".sex").text(userItem.sex);
					user.find(".hobbys").text(userItem.hobbys);
					user.find(".birthday").text(userItem.birthday);
					user.find(".city").text(userItem.city);
					user.find(".qq").text(userItem.qq);
					user.find(".email").text(userItem.email);
					user.find(".time").text(userItem.createtime);

					user.find(".delete").attr("onclick",
							"deleteUser(" + userItem.userid + ")");
					user.find(".restore").attr("onclick",
							"restoreUser(" + userItem.userid + ")");
					if (userItem.state == 0) {
						user.find(".delete").show();
						user.find(".restore").hide();
					} else {
						user.find(".delete").hide();
						user.find(".restore").show();
					}
					$(".list").append(user);
				});
				num++;
				//alert(date2.totalPage)
				page = setPage(pageNum, date2.totalPage, "getUser");
				pageNum++;
			}
		});
	}
	function deleteUser(userid) {
		$.ajax({
			url : "AdminUserServlet",
			data : {
				'action' : "deleteuser",
				'userid' : userid
			},
			dataType : 'json',
			type : 'post',
			success : function(date) {
				getUser(1);
			//window.location.replace("/muke/admin/usermanager.jsp");
			}
		});
	}
	function restoreUser(userid) {
		$.ajax({
			url : "AdminUserServlet",
			data : {
				'action' : "restoreuser",
				'userid' : userid
			},
			dataType : 'json',
			type : 'post',
			success : function(date) {
				getUser(1);
				//window.location.replace("/muke/admin/usermanager.jsp");
			}
		});
	}
	function search() {
		name = $("input[name='username']").val();
		getUser(1);
	}
</script>


</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 msgtitle">
				<h3>
					<span class="title">用户管理</span>
				</h3>
				<div class="replybtn">
					<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#search">搜索</button>
					
				</div>
			</div>
		</div>
<div class="col-sm-12">
			<table class="table table-striped">
				<tbody class="list">
					<tr class="" style="display: table-row;">
				</tbody>
			</table>
		</div>
		<table class="table table-striped">
			<tbody>
				<tr class="template">
					<td class="userinfo num">1</td>
					<td class="title name">用户名</td>
					<td class="userinfo realname">昵称</td>
					<td class="userinfo sex">性别</td>
					<td class="userinfo hobbys">爱好</td>
					<td class="userinfo birthday">生日</td>
					<td class="userinfo city">城市</td>
					<td class="userinfo qq">QQ</td>
					<td class="userinfo text-limit email tooltip-test"
						data-toggle="tooltip" title="@foxmail.com">邮箱</td>
					<td class=" userinfo time">2017-01-01</td>
					<td>
						<button class="btn btn-danger delete">删除</button>
						<button class="btn btn-warning restore">恢复</button>
					</td>
				</tr>
			</tbody>
		</table>

		<div class="row" style="text-align: center">
			<jsp:include page="/page/pagetool.jsp"></jsp:include>
		</div>


	</div>


	<!-- 模态框（Modal） -->
	<div class="modal fade" id="search" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modalcenter">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="replyLabel">搜索</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="username">用户名：</label> <input type="text"
								class="form-control" id="username" name="username"
								placeholder="">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="search()">搜索</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>