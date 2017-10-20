<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
   	<title>404页面</title>
	<style type="text/css">
		body {
  background: #000;
  height: 100vh;
  overflow: hidden;
}

#ui {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 230px;
  height: 230px;
  background: #000;
  transform: translate(-50%, -50%);
  filter: blur(5px) contrast(30);
  overflow: hidden;
}
#ui .loader {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 90%;
  height: 90%;
  border: 12px solid #fff;
  overflow: hidden;
  transform: translate(-50%, -50%);
  box-sizing: border-box;
  border-radius: 100%;
}
#ui .loader::before {
  content: '404 404  404';
  position: absolute;
  top: 50%;
  left: 50%;
  color: #fff;
  font-size: 7rem;
  font-family: 'Dosis', sans-serif;
  letter-spacing: 2px;
  white-space: nowrap;
  transform: translate(100%, -60%);
  animation: marquee 10000ms infinite linear;
  text-shadow: 0px 60px 10px rgba(255, 255, 255, 0.6);
}
#ui .loader::after {
  content: '';
  position: absolute;
  bottom: -20px;
  left: 50%;
  width: 42px;
  height: 42px;
  margin-left: -21px;
  background: #fff;
  border-radius: 100%;
  transform-origin: 50% -78px;
  transform: rotateZ(90deg);
  animation: rotate 10000ms infinite linear;
}

@keyframes marquee {
  from {
    transform: translate(-34%, -50%);
  }
  to {
    transform: translate(-68%, -50%);
  }
}
@keyframes rotate {
  from {
    transform: rotateZ(0deg);
  }
  to {
    transform: rotateZ(360deg);
  }
}

	</style>
  </head>
  
  <body>
    <div id="ui">
	  <div class="loader"></div>
	</div>
  </body>
</html>
