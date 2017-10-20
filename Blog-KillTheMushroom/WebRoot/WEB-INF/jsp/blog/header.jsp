<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<header id="header" class="home-header blog-background banner-mask">
	<div class="header-container">
		<div class="nav-header container">
			<div class="nav-header-container">
				<a class="back-home" href="${basePath }index">首页</a> <a
					class="back-home" href="${basePath }index">文章</a> <a
					class="back-home" href="${basePath }tag">标签</a> <a
					class="back-home" href="${basePath }message">留言板</a> <a
					class="back-home" href="${basePath }chatroom">聊天室</a> <a
					class="back-home" href="${basePath }about">关于</a>
			</div>
		</div>
		<div class="header-wrap">
			<div class="container">
				<div class="home-info-container">
					<a href="index.htm"> <h2>KillTheMushroom</h2> </a>
					<h4 id="web_title_desc">蘑菇？杀死！</h4>
					<div class='log-box'>
						<c:choose>
							<c:when test="${USER_ONLINE==null }">
								<a class="reg" href="${basePath }register">注册</a>
								<a class="login" href="${basePath }login">登录</a>
							</c:when>
							<c:otherwise>
								<a class="reg" href="javascript:;">${USER_ONLINE.nickname }</a>
								<a class="login" href="${basePath }login">退出</a>
							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<script src="https://cdn.bootcss.com/three.js/r83/three.js"></script>
<script src="${basePath }static/tpanorama/js/tpanorama.js"></script>
<script>
var opt,tp;
window.onload = function () {
    opt = {
        container:'header',//容器
        url:'${basePath}static/tpanorama/img/p5.jpg',
		width: '100px',//指定宽度，高度自适应
		height: '300',//单位固定为px
        widthSegments: 60,//水平切段数
        heightSegments: 40,//垂直切段数（值小粗糙速度快，值大精细速度慢）
        pRadius: 1000,//全景球的半径，推荐使用默认值
        minFocalLength: 6,//镜头最a小拉近距离
        maxFocalLength: 6,//镜头最大拉近距离
        showlable: 'click' // show,click
    }
    tp = new tpanorama(opt);
    tp.init();
}
</script>