<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>尾巴</title>
</head>
<body>
<div class="footer">
		<div class="container">
			<div class="row footer-top">
				<div class="col-sm-6 col-lg-6">
					<h3>慕课答疑平台</h3>
					<p>本网站所列开源项目的中文版文档全部由Bootstrap中文网成员翻译整理，并全部遵循 CC BY 3.0协议发布。</p>
				</div>
				<div class="col-sm-6  col-lg-5 col-lg-offset-1">
					<div class="row about">
						<div class="col-xs-3">
							<h4>关于</h4>
							<ul class="list-unstyled">
								<li><a href="#">关于我们</a></li>
								<li><a href="#">广告合作</a></li>
								<li><a href="#">友情链接</a></li>
								<li><a href="#">招聘</a></li>
							</ul>
						</div>
						<div class="col-xs-3">
							<h4>联系方式</h4>
							<ul class="list-unstyled">
								<li><a href="#"
									title="官方微博" target="_blank">新浪微博</a></li>
								<li><a href="mailto:#">电子邮件</a></li>
							</ul>
						</div>
						<div class="col-xs-3">
							<h4>旗下网站</h4>
							<ul class="list-unstyled">
								<li><a href="#" target="_blank">Laravel中文网</a></li>
								<li><a href="#" target="_blank">Ghost中国</a></li>
							</ul>
						</div>
						<div class="col-xs-3">
							<h4>赞助商</h4>
							<ul class="list-unstyled">
								<li><a href="# target="_blank">又拍云</a></li>
							</ul>
						</div>
					</div>

				</div>

			</div>
			<hr/>
			<div class="row footer-bottom">
				<ul class="list-inline text-center">
					<li><a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备11008151号</a></li>
					<li>京公网安备11010802014853</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>