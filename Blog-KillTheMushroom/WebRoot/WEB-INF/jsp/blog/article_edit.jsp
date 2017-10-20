<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set value="<%=basePath %>" var="basePath"></c:set>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>KillTheMushroom</title>
<link href="static/layui/css/layui.css" rel="stylesheet">
<link href="static/m-finder.com/public/css/home.css" rel="stylesheet">
<link rel="icon" href="static/images/favicon.ico">

<script type="text/javascript"
	src="static/wangEditor/release/wangEditor.js"></script>
<script src="static/layui/layui.js"></script>
<script src="static/m-finder.com/public/js/jquery-3.2.1.min.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<style>
.layui-layout-admin .layui-header{
	background-color : #ffffff;
	border-bottom: 1px solid rgba(0, 0, 0, .08);
}
.layui-logo img{
	height:55px;
	width:auto;
}
.layui-layout-admin .layui-nav .layui-nav-item a{
	color:#808080;
}
.wjw-row{
	padding:16px 0;
}
.wjw-input{
	height: 44px;
    min-height: 44px;
    display: block;
    width: 100%;
    border: 0;
    font-size: 32px;
    line-height: 1.4;
    font-weight: 700;
    outline: none;
    -webkit-box-shadow: none;
    box-shadow: none;
}
.layui-form-selected dl{
	z-index: 999999;
}
.wjw-form .layui-form-select {
    position: relative;
    float: left;
    margin-right: 18px;
    color: #808080;
}
.wjw-form .layui-form-label {
    padding: 9px 15px 9px 0;
    width: auto;
    color: #808080;
}

</style>
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">
				<img src="${basePath }static/images/logo.png">
			</div>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href=""><i class="layui-icon">&#xe615;</i> 预览</a>
				</li>
				<li class="layui-nav-item">
					<button class="layui-btn layui-btn-primary">发表文章</button>
				</li>
			</ul>
		</div>
	</div>
		<div class="layui-row layui-form wjw-form">
			<div class="layui-col-md8 layui-col-md-offset2">
				<div class="wjw-row">
					<input class="wjw-input" name="title" placeholder="请输入标题">
				</div>
				<div class="layui-form-item">
				  	<label class="layui-form-label">文章类型</label>
				  	<select name="classifyId" lay-verify="required">
				        <option value=""></option>
				        <option value="0">原创</option>
				        <option value="1">转载</option>
				        <option value="2">翻译</option>
			      	</select>
				  	<select name="privacy" lay-verify="required">
				        <option value=""></option>
				        <option value="0">分类一</option>
			      	</select>
				</div>
				<div id="editor" style="width:100%;"></div>
				
			</div>
		</div>
		


	<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#editor');
   	// 隐藏“网络图片”tab
    editor.customConfig.showLinkImg = false;
 	// 配置服务器端地址
    editor.customConfig.uploadImgServer = '<%=basePath%>m/admin/upload';
	// 将 timeout 时间改为 20s
	editor.customConfig.uploadImgTimeout = 20000;
	//其他配置项
	editor.customConfig.zIndex = 100;
	editor.customConfig.areaHeight = 'calc(100% - 235px)';
	// 图片上传监听 函数
	editor.customConfig.uploadImgHooks = {
		before : function(xhr, editor, files) {
		},
		success : function(xhr, editor, result) {
			console.log(result);
		},
		fail : function(xhr, editor, result) {
			console.log(result);
		},
		error : function(xhr, editor) {
		},
		timeout : function(xhr, editor) {
		},
		customInsert : function(insertImg, result, editor) {
			var url = result.object;
			insertImg(url);
		}
	}
	//创建dom
	editor.create();

	//获取内容
	editor.txt.html();
	
	layui.use('form', function(){
	  var form = layui.form;
	  
	  form.render('select'); //刷新select选择框渲染
	});
</script>
</body>


</html>
