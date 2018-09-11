<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<% 
	String msgId = request.getParameter("msgid");
	if (msgId == null || msgId.equals("")){
		msgId = "1";
	}
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
var pageNum = 1;
var msgId =<%=msgId%>;
$(function() {
	getMsg();
	getReply();
});
function validateForm() {
	// 验证表单
	validateForm();
}
function getMsg() {
	// Ajax 获取问题信息
	$.ajax({
		url : "MessageServlet",
		data : { 'action' : "getMsg", 'pagenum' : pageNum, 'msgid' : msgId },
		dataType : 'json',
		type : 'post',
		success : function(date) {
			//var date2 = eval(date);
			var title = $(".msgtitle").clone();
			title.show();
			//title.removeClass("msgtitle");
			title.find(".title").text(date.msgtopic);
			title.find(".badge").text(date.thename);
			if (date.state == 1){
				title.find(".wonderful").text("精");
			}
			//title.css("list-style-type", "none");
			var msg = $(".template").clone();
			msg.show();
			msg.removeClass("template");
			msg.find(".order").text("楼主");
			msg.find(".author").text(date.realname);
			msg.find(".msgcontent").text(date.msgcontents);
			msg.find(".time").text(date.msgip + " • " + date.msgtime);
			
			$(".titlerow").prepend(title);
			$(".titlerow").after(msg);
		}
	});
}
function getReply() {
	// Ajax 获取回复信息
	$.ajax({
		url : "MessageServlet",
		data : { 'action' : "getReply", 'pagenum' : pageNum, 'msgid' : msgId },
		dataType : 'json',
		type : 'post',
		success : function(date) {
			var date2 = eval(date);
			var lou = 0;
			//	alert(date2.data[0].replycontents);
			$.each(date2.data, function(index, msgItem) {
				lou++;
				var msg = $(".template").clone();
				msg.show();
				msg.removeClass("template");
				msg.find(".order").text(lou + "楼");
				msg.find(".author").text(msgItem.realname);
				msg.find(".sex").text(msgItem.sex);
				msg.find(".city").text(msgItem.city);
				msg.find(".msgcontent").text(msgItem.replycontents);
				msg.find(".time").text(
						msgItem.replyip + " • " + msgItem.replytime);
				msg.find(".deletereply").text("删除");
				msg.find(".deletereply").attr("href", "javascript:dele("+msgItem.replyid+","+msgItem.userid+")");
				$(".template").before(msg);
			});
			pageNum++;
			if (parseInt(date.totalPage) >= parseInt(pageNum)) {
				$("#loadmore").html("加载更多...");
			} else {
				$("#loadmore").html("没有更多数据了！");
			}
		}
	});
}

function replyMsg() {

	var replycontent = $("#replycontent").val();
	//alert(replycontent);
	$.ajax({
		url : "UserMessageServlet",
		data : {"action" : "replyMsg", "msgid" : msgId, "rc" : replycontent },
		dataType : "json",
		type : "post",
		success : function(date) {
			if (date.success == "true") {
				alert("回复成功");
				window.location.replace("/muke/message.jsp?msgid="+msgId);
			} else if (date.success = "false") {
				alert("回复失败!");
			}
		}
	});
}
function newmsg() {
	window.location.replace("/muke/newmsg.jsp");
}
function dele(replyid,userid) {
	$.ajax({
		url : "MessageServlet", 
		type : "POST",
		async : "true",
		data : {"action" : "del", "msgid" : msgId, "replyid": replyid, "userid": userid },			
		dataType : "json",
		success : function(data) {
			if (data.res == 1){
				alert("删除成功");
				window.location.replace("newmsg.jsp");
			}else if (data.res == -2){
				alert("请先登录");
			}else {
				alert("删除失败");			
			}
		}
	});		
}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container" id="msgList">
	
		<div class="row titlerow">
			<div class="col-sm-12 msgtitle" style="display: none">
			
				<h3>
					<span class="title"></span>&nbsp;&nbsp;
					<span class="label label-danger wonderful"></span>&nbsp;&nbsp;
					<span class="badge"></span>
				</h3>
				
				<div class="replybtn">
					<c:if test="${sessionScope.user == null}">
						<button type="button" class="btn btn-success" data-toggle="modal"
							onclick="alert('请先登录！')">回复</button>
						<button type="button" class="btn btn-success" data-toggle="modal"
							onclick="newmsg()">返回</button>
					</c:if>
					<c:if test="${sessionScope.user != null}">
						<button type="button" class="btn btn-success" data-toggle="modal"
							data-target="#reply">回复</button>
						<button type="button" class="btn btn-success" data-toggle="modal"
							onclick="newmsg()">返回</button>
					</c:if>

				</div>
			</div>
		</div>
		<div class="row reply template">
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo order">0楼</div>
			</div>
			<div class="col-sm-2 col-xs-2">
				<div class="author"></div>
				<div class="sex"></div>
				<div class="city"></div>
			</div>
			<div class="col-sm-10 col-xs-10">
				<div class="msgcontent"></div>
			</div>
			<div class="col-sm-12" style="overflow: hidden;">
				<div class="rightinfo">
				<span class="time"></span>
				<a class="deletereply"></a>
				</div>
			</div>
		</div>

	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<br />
				<button id="loadmore" type="button"
					class="btn btn-default btn-lg btn-block"
					onclick="javascript:getReply();">加载更多...</button>
			</div>
		</div>
	</div>


	<!-- 模态框（Modal） -->
	<div class="modal fade" id="reply" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modalcenter">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="replyLabel">回复：</h4>
				</div>
				<div class="modal-body">
					<!-- 
	    			<textarea style="width:100%" rows="5" cols="" id="replycontent"></textarea>
	    			 -->
					<form id="replyform">
						<div class="form-group">
							<textarea style="width: 100%" rows="5" cols=""
								name="replycontent" id="replycontent"></textarea>
						</div>
						<div class="text-right">
							<span id="returnMessage" class="glyhicon"></span>
							<p></p>
							<button class="btn btn-default" data-dismiss="modal">关闭</button>
							<button class="btn btn-primary" data-dismiss="modal" onclick="replyMsg();">提交</button>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>