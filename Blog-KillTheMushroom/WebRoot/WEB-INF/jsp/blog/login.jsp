<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>KillTheMushroom</title>
<script src="static/layui/layui.js"></script>
<script src="static/m-finder.com/public/js/jquery-3.2.1.min.js"></script>
<link href="static/layui/css/layui.css" rel="stylesheet">
<link href="static/m-finder.com/public/css/home.css" rel="stylesheet">
<link rel="icon" href="static/images/favicon.ico">
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">
				<a class="navbar-brand" href="${basePath }index">KTM</a>
			</div>
		</div>
		<div class="content-area container login-content" style="width:100%">
			<div class="layui-tab">
				<ul class="layui-tab-title">
					<li class="layui-this">登录</li>
					<li></li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<form class="layui-form layui-form-pane login-box" method="POST"
							action="javascript:;">
							<div class="layui-form-item">
								<label class="layui-form-label">用户名</label>
								<div class="layui-input-inline">
									<input id="username" class="layui-input"
										lay-verify="required" name="username"  autofocus
										autocomplete="off" placeholder="请输入用户名">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">密码</label>
								<div class="layui-input-inline">
									<input type="password" name="password" lay-verify="required"
										autocomplete="off" placeholder="请输入密码" class="layui-input"
										autofocus autocomplete="off">
								</div>
							</div>

							<div class="layui-form-item login-btn-box">
								<button class="layui-btn" lay-submit="" lay-filter="login">立即登录</button>
								<span style="padding-left:20px;">
								<a href="${basePath }/password/reset">忘记密码？</a>
								</span>
							</div>
						</form>
					</div>
				</div>
			</div>
			<script>
			    layui.use(['form', 'layer'], function () {
			        var form = layui.form, layer = layui.layer;
			        
			        form.on('submit(login)',function(data){
			        	$.ajax({
			        		url : '${basePath}login/do',
			        		data : data.field,
			        		type : 'post',
			        		dataType : 'json'
			        	}).done(function(resp){
			        		if(resp.code==0){
			        			window.location.href='${basePath}index';
			        		}else{
			        			layer.alert(resp.msg);
			        		}
			        	}).fail(function(e){
			        		layer.msg(JSON.stringfy(e));
			        	})
			        })
			    });
			</script>

		</div>
	</div>

	<%@ include file="footer.jsp" %>
</body>
</html>

