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
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
	$(function(){
		$("#loginform").bootstrapValidator({
		 	message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                name: {/*键名name和input name值对应*/
                    message: 'The name is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '用户名不能为空'
                        },
                        regexp: {
                        	regexp: /^[a-zA-Z0-9_\.]+$/,
                        	message: '用户名格式错误'
                        },
                        stringLength: {/*长度提示*/
                            min: 6,
                            max: 16,
                            message: '用户名长度必须在6到16之间'
                        }/*最后一个没有逗号*/
                        
                    }
                },
                pwd: {
                    message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 16,
                            message: '密码长度必须在6到30之间'
                        }
                    }
                }
            }
        });
	});
	function login(){
		// Ajax 异步请求登录
		var name = $("input[name='name']").val();
		var pwd = $("input[name='pwd']").val();
		
		$.ajax({
			url : "AdminServlet", 
			type : "POST",
			async : "true",
			data : {"action" : "login", "name": name, "pwd": pwd},
			dataType : "json",
			success : function(data) {
				if (data.res == 1){
					alert("登录成功");
					window.location.replace("admin/index.jsp");
				}
				else {
					$(".text-warning").text(data.info);
					$("input[name='name']").val("");
					$("input[name='pwd']").val("");
				}
			}
		});		
		return false;
	}
</script>
</head>
<body>
	<jsp:include flush="fasle" page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>管理员登录</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="loginform" method="post">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">账号：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="name" placeholder="请输入账号">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="pwd" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="help-block">${error}</span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4 col-xs-12">
					<button class="btn btn-success btn-block"  onclick="login();">登录</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include flush="fasle" page="footer.jsp" />
</body>
</html>