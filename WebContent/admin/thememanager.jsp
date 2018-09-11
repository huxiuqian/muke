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
		getTheme(pageNum);

	});
	function getTheme(pageNum) {
		$.ajax({
			url : "AdminThemeServlet",
			data : {
				'action' : "search",
				'pagenum' : pageNum,
				'key' : name
			},
			dataType : 'json',
			type : 'post',

			success : function(date) {
				var date2 = eval(date);

				var num = 1;
				$(".list").html("");
				$.each(date2.data, function(index, themeItem) {

					var theme = $(".template").clone();
					theme.show();
					theme.removeClass("template");
					theme.find(".num").text(index + 1);
					theme.find(".name").text(themeItem.thename);

					theme.find(".delete").attr("onclick",
							"deleteTheme(" + themeItem.theid + ")");
					theme.find(".restore").attr("onclick",
							"restoreTheme(" + themeItem.theid + ")");

					if (themeItem.state == 0) {
						theme.find(".delete").show();
						theme.find(".restore").hide();
						
					} else {
						theme.find(".delete").hide();
						theme.find(".restore").show();
					}

					$(".list").append(theme);

				});
				num++;

				//alert(date2.totalPage)
				page = setPage(pageNum, date2.totalPage, "getTheme");
				pageNum++;
			}

		});
	}
	function deleteTheme(theid) {
		$.ajax({
			url : "AdminThemeServlet",
			type : "post",
			async : "true",
			data : {
				"action" : "deletetheme",
				"theid" : theid
			},
			dataType : "json",
			success : function(data) {

				getTheme(pageNum)

			}
		});
	}
	function restoreTheme(theid) {
		$.ajax({
			url : "AdminThemeServlet",
			type : "post",
			async : "true",
			data : {
				"action" : "restoretheme",
				"theid" : theid
			},
			dataType : "json",
			success : function(data) {

				getTheme(pageNum)

			}
		});
	}
	function add() {
		var addname = $("input[name='addthename']").val();
		$.ajax({
			url : "AdminThemeServlet",
			data : {
				'action' : "add",
				'name' : addname
			},
			dataType : 'json',
			type : 'post',

			success : function(data) {
				if (data.res == 1) {
					alert("添加成功");
					getTheme(1);
				} else {
					alert("添加失败");

				}
			}

		});

	}

	function search() {
		name = $("input[name='thename']").val();

		getTheme(1);
	}
</script>


</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 msgtitle">
				<h3>
					<span class="title">主题管理</span>
				</h3>
				<div class="replybtn">
					<button type="button" class="btn btn-warning" data-toggle="modal"
							data-target="#add">添加</button>
					<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#search">搜索</button>
					
				</div>
			</div>
		</div>
<div class="list"></div>


		<div class="row msglist template">
			<div class="col-sm-1 col-xs-1 text-center num">1</div>


			<div class="col-sm-8 col-xs-8">
				<div class="col-sm-8 col-xs-12 name">
					<a class="tit" target="_blank">标题</a>
				</div>

			</div>
			<div class="col-sm-3  col-sm-offset-0 col-xs-offset-5 col-xs-7 ">
				<button class="btn btn-danger delete">删除</button>
				<button class="btn btn-warning restore">恢复</button>

			</div>
		</div>



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
					<h4 class="modal-title">搜索</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="thename">主题名：</label> <input type="text"
								class="form-control" id="thename" name="thename" placeholder="">
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

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="add" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modalcenter">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加主题</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group">
							<label for="thename">主题名：</label> <input type="text"
								class="form-control" id="addthename" name="addthename"
								placeholder="">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="add()">添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>