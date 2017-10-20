<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="<%=basePath %>m/admin/upload" method="post" enctype="multipart/form-data">
    	<input type="file" name="image">
    	<input type="submit" name="submit">
    </form>
    
    <div id="editor">
        <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
    </div>

    <!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
    <script type="text/javascript" src="static/wangEditor/release/wangEditor.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')
       	// 隐藏“网络图片”tab
        editor.customConfig.showLinkImg = false
     	// 配置服务器端地址
        editor.customConfig.uploadImgServer = '<%=basePath %>m/admin/upload'
       	// 将 timeout 时间改为 20s
       	editor.customConfig.uploadImgTimeout = 20000
        // 图片上传监听 函数
       	editor.customConfig.uploadImgHooks = {
       	    before: function (xhr, editor, files) {
       	        // 图片上传之前触发
       	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
       	        
       	        // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
       	        // return {
       	        //     prevent: true,
       	        //     msg: '放弃上传'
       	        // }
       	    },
       	    success: function (xhr, editor, result) {
       	        // 图片上传并返回结果，图片插入成功之后触发
       	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
       	        console.log(result);
       	    },
       	    fail: function (xhr, editor, result) {
       	        // 图片上传并返回结果，但图片插入错误时触发
       	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
       	        console.log(result);
       	    },
       	    error: function (xhr, editor) {
       	        // 图片上传出错时触发
       	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
       	    },
       	    timeout: function (xhr, editor) {
       	        // 图片上传超时时触发
       	        // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
       	    },

       	    // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
       	    // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
       	    customInsert: function (insertImg, result, editor) {
       	        // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
       	        // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

       	        // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
       	        var url = result.object
       	        console.log();
       	        insertImg(url)

       	    }
       	}
        
        //创建dom
        editor.create()
    </script>
    
  </body>
</html>
