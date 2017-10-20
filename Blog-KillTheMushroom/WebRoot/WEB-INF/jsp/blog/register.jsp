<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8"utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="csrf-token" content="GrrWtA1vTWDlXuXG42yHpPyrYd6Yvtav83BcGYKf">
        <title>M-finder - M-finder</title>
        <script src="public/js/layui/layui.js" tppabs="http://www.m-finder.com/public/js/layui/layui.js"></script>
        <script src="public/js/jquery-3.2.1.min.js" tppabs="http://www.m-finder.com/public/js/jquery-3.2.1.min.js"></script>
        <script src="public/js/common.js" tppabs="http://www.m-finder.com/public/js/common.js"></script>
        <script src="public/js/home.js" tppabs="http://www.m-finder.com/public/js/home.js"></script>
		<script src="public/js/musicplayer.js" tppabs="http://www.m-finder.com/public/js/musicplayer.js"></script>
        <link href="public/js/layui/css/layui.css" tppabs="http://www.m-finder.com/public/js/layui/css/layui.css" rel="stylesheet">
        <link href="public/css/global.css" tppabs="http://www.m-finder.com/public/css/global.css" rel="stylesheet">
        <link href="public/css/home.css" tppabs="http://www.m-finder.com/public/css/home.css" rel="stylesheet">
        <link rel="icon" href="http://www.m-finder.com/public/favicon.ico"> 
    </head>
    <body>
        <div class="layui-layout layui-layout-admin">
            <div class="layui-header">
                <div class="layui-logo">
                    <a class="navbar-brand" href="index.htm" tppabs="http://www.m-finder.com/">M-finder</a>
                </div>
            </div>

            <div class="content-area container login-content" style="width:100%">
                <div class="layui-tab">
    <ul class="layui-tab-title">
        <li class="layui-this">注册</li>
        <li></li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">

            <form class="layui-form layui-form-pane login-box" method="POST" action="http://www.m-finder.com/register" id="login-box">
                <input type="hidden" name="_token" value="GrrWtA1vTWDlXuXG42yHpPyrYd6Yvtav83BcGYKf">
                <div class="layui-form-item ">
                    <label class="layui-form-label">用户名称</label>
                    <div class="layui-input-inline">
                        <input id="name" type="text" class="layui-input" lay-verify="required|name" name="name" value="" autofocus autocomplete="off" placeholder="请输入用户名称">
                    </div>
                                    </div>
                <div class="layui-form-item ">
                    <label class="layui-form-label">登录邮箱</label>
                    <div class="layui-input-inline">
                        <input id="email" type="text" class="layui-input" lay-verify="required|email" name="email" value="" autofocus autocomplete="off" placeholder="请输入登录邮箱">
                    </div>
                                    </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">登录密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" id="password" lay-verify="password" autocomplete="off" placeholder="请输入登录密码" class="layui-input" autofocus autocomplete="off">
                    </div>
                                    </div>

                <div class="layui-form-item login-btn-box">
                    <button class="layui-btn" lay-submit="" lay-filter="login-box">提交注册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form, layer = layui.layer;
        //自定义验证规则
        form.verify({
            name: function (val) {
                if (val === '' || $.trim(val).length == 0 || $.trim(val).length < 2 || $.trim(val).length > 20) {
                    return '用户名长度2-20';
                }
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(val)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(val)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if (/^\d+\d+\d$/.test(val)) {
                    return '用户名不能全为数字';
                }
            }
            , password: [/(.+){6,32}$/, '密码必须6到32位']
        });
    });
</script>

            </div>
        </div>

       <%@ include file="footer.jsp" %>
    </body>
</html>
