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
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
$(function(){
	getTheme();
	validator();
});

function validator(){
	$("#addmsgform").bootstrapValidator({
	 	message: 'This value is not valid',
        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {/*验证*/
        	msgtopic: {/*键名username和input name值对应*/
                message: 'The msgtopic is not valid',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '标题不能为空'
                    },
                    stringLength: {/*长度提示*/
                        min: 6,
                        max: 200,
                        message: '标题长度必须在6到200之间'
                    }/*最后一个没有逗号*/

                }
            },
            msgcontents: {
                message:'The msgcontents is not valid',
                validators: {
                    notEmpty: {
                        message: '问题内容不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 1000,
                        message: '问题内容长度必须在6到1000之间'
                    }
                }
            }
        }
    });
}

function getTheme(){
	
	$.ajax({
		url : "UserMessageServlet", 
		type : "POST",
		async : "true",
		data : {"action" : "getTheme"},
		dataType : "json",
		success : function(data) {
			$("select[name='theid']").append($("<option value='-1'>未选择</option>"));
			$.each(data.data, function(index, themeItem) {
				$("select[name='theid']").append($("<option value='"+themeItem.theid+"'>"+themeItem.thename+"</option>"));
			});
			
			validator();
		}
	});
}
function addmsg(){
	// 提交前先主动验证表单 
	var bv = $("#addmsgform").data('bootstrapValidator');
	bv.validate();
	if (!bv.isValid()){
		return;
	}
	
	var msgtopic = $("input[name='msgtopic']").val();
	var theid = $("select[name='theid']").val();
	var msgcontents = $("textarea[name='msgcontents']").val();
	var user = $("sessionScope.user").val();
	
	$.ajax({
		url : "UserMessageServlet", 
		type : "POST",
		async : "true",
		data : {"action" : "add", "user": user, "msgtopic" : msgtopic, "theid" : theid, "msgcontents" : msgcontents},
		dataType : "json",
		success : function(data) {
			if (data.res == 1){
				alert("提问成功");
				window.location.replace("");
			}
			else {
				$(".text-warning").text(data.info);
				$("input[name='username']").val("");
				$("input[name='password']").val("");
			}
		}
	});
	
	return false;
}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="../header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 text-center">
				<h3>我要提问</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-2" id="addmsgform" method="post">
			<div class="form-group">
				<label for="msgtopic" class="col-sm-2 control-label">标题：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="msgtopic" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label for="theid" class="col-sm-2 control-label">主题：</label>
				<div class="col-sm-6">
					<select class="form-control" name="theid">
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label for="msgcontents" class="col-sm-2 control-label">内容：</label>
				<div class="col-sm-6">
					<textarea class="form-control" rows="5" name="msgcontents"></textarea>
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-3 col-xs-12">
					<button class="btn btn-success" onclick="addmsg();">提问</button>
					<button type="reset" class="btn btn-default">重置</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include flush="fasle" page="../footer.jsp" />
</body>
</html>