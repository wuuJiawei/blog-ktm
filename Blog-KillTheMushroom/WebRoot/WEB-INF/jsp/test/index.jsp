<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script src="js/ga.js"></script>
  </head>
  
  <body> 
  	<hr>
  	<button class="layui-btn">预约BOOK-创建任务</button>
    <i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe60c;</i>  
    <form action="<%=basePath %>job/book/create" method="post" enctype="multipart/form-data">
    	创建人createUserId：<input type="number" value="8" name="createUserId"><br>
    	被预约模特targetUserId：<input type="number" value="8" name="targetUserId"><br>
    	活动名称：<input type="text" value="名称测试" name="jobName"><br>
    	活动地址：<input type="text" value="地点 测试" name="jobAddress"><br>
    	价格类型chargeNumber：<input type="text" value="0" name="chargeType"><small>0-时薪，1-日薪</small><br>
    	需求的类型userType：<input type="text" value="1,3,4" name="userType"><small>1-化妆师/摄影师，3-模特，4-网红KOL</small><br>
    	单价chargePrice：<input type="text" value="5000" name="chargePrice"><br>
    	beginTime：<input type="text" value="2017-09-11 12:10" name="beginTime"><br>
    	endTime：<input type="text" value="2017-09-13 14:09" name="endTime"><br> 
    	详情描述：<input type="text" value="描述 测试" name="jobContent"><br>
    	<input type="file" name="image">
    	<input type="file" name="image">
    	<input type="file" name="image"><br>
    	<input type="submit" value="上传">
    </form>
    <hr>
  	<button class="layui-btn">上传头像</button>
    <form action="<%=basePath %>user/upload/headpic?userId=8" method="post" enctype="multipart/form-data">
    	<input type="file" name="headpic">
    	<input type="submit" value="SUBMIT">
    </form>
    <hr>
    <h3>上传相册</h3>
    <form action="<%=basePath %>album/upload?userId=8" method="post" enctype="multipart/form-data">
    	<input type="text" value="0" name="fileType"> 0-图片 1-视频
    	<input type="file" name="files">
    	<input type="file" name="files">
    	<input type="file" name="files">
    	<input type="submit" value="SUBMIT">
    </form>
    
    <hr>
    <button class="layui-btn">创建任务</button>
    <form action="<%=basePath %>job/insert" method="post" enctype="multipart/form-data">
    	创建人：<input type="number" value="8" name="createUserId"><br>
    	活动名称：<input type="text" value="名称测试" name="jobName"><br>
    	活动地址：<input type="text" value="地点 测试" name="jobAddress"><br>
    	价格类型：<input type="text" value="0" name="chargeType"><small>0-时薪，1-日薪</small><br>
    	需求的类型userType：<input type="text" value="1,3,4" name="userType"><small>1-化妆师/摄影师，3-模特，4-网红KOL</small><br>
    	单价：<input type="text" value="5000" name="chargePrice"><br>
    	beginTime：<input type="text" value="2017-09-11 12:00" name="beginTime"><br>
    	endTime：<input type="text" value="2017-09-13 14:30" name="endTime"><br> 
    	详情描述：<input type="text" value="描述述述述述述述述述述述" name="jobContent"><br>
    	又拍云图片名称<input type="text" value="123.jpg,234.jpg,345.jpg" name="jobImage"><br>
    	<input type="file" name="image">
    	<input type="file" name="image">
    	<input type="file" name="image"><br>
    	<input type="submit" value="SUBMIT">
    </form>
   
    <hr>
    <h3>任务评价</h3>
    <form action="<%=basePath %>job/comment/insert" method="post" enctype="multipart/form-data">
    	Userid<input type="text" value="1" name="userId"><br>
    	recordId<input type="text" value="1" name="recordId"><br>
    	commentTag：<input type="text" value="Perfect" name="commentTag"><br>
    	commentContent：<input type="text" value="好评 测试" name="commentContent"><br>
    	point：<input type="text" value="3" name="point"><br>
    	<input type="file" name="image">
    	<input type="file" name="image">
    	<input type="file" name="image">
    	<input type="submit" value="SUBMIT">
    </form>
  </body>
</html>
