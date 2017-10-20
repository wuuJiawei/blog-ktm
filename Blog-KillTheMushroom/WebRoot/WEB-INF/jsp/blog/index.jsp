<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set value="<%=basePath %>" var="basePath"></c:set>

<!doctype html>
<html lang="cmn-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="M-finder博客，laravel，layui，技术分享，原创" />
<meta name="description" content="M-finder" />
<title>Blog-KillTheMushroom</title>

<script src="static/layui/layui.js"></script>
<script src="static/js/jquery-3.2.1.min.js"></script>
<script src="static/js/common.js"></script>
<script src="static/js/3d_lion.js"></script>

<link href="static/css/common.css" rel="stylesheet">
<link href="static/layui/css/layui.css" rel="stylesheet">
<link href="static/m-finder.com/public/css/home.css" rel="stylesheet">


<link rel="icon" href="static/images/favicon.ico">

</head>
<body class="home blog ">
	<div class="hfeed site">
		<%@ include file="header.jsp" %>
	</div>

	<div class="main layui-clear ">
		<div id="main" class="content homepage">
			<div class="content-area container ">
				<div class="site-content" id="article_content">
					<div class="content-area container">
						<header class="entry-header page-header">
							<h1 class="entry-title page-title">${article.title }</h1>
							<div class="entry-meta">
								<time class="entry-date published updated"><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></time>
								<span class="separator">/</span> ${article.readNum }阅 <span class="separator">/</span>
								<span id='like_num'>${article.thumb }</span> 赞
							</div>
						</header>
						<div class="site-content">
							<section class="post-content">
								<div class="single-post-inner grap detail-body photos">
									${article.content }
								</div>
							</section>

							<div class="post-actions">
								<a href='javascript:;' onclick="like_this(this)"><i
									class="layui-icon" style="font-size: 20px;  ">&#xe6c6;</i> </a>
							</div>
							<div class="author-field u-textAlignCenter">
								<img src="${basepath }${article.user.headpic }" width="64" height="64"  />
								<h3>${article.user.nickname }</h3>
							</div>
						</div>
					</div>
				</div>

				<div class="site-content" style='padding-top:5px;'>
					<div class="content-area container">
						<div id="comments" class="comments-area">
							<h2 class="comments-title layui-hide" id='comment-title'>条评论</h2>
							<ol class="comment-list layui-hide " id='comment_list'></ol>
							<section class="post-content">
								<div class="single-post-inner grap">
									<div id="comment-page"></div>
								</div>
							</section>
							<div id="respond" class="comment-respond">
								<h3 id="reply-title" class="comment-reply-title">发表评论</h3>
								<form id="commentform" class="comment-form">
									<p class="comment-form-comment">
										<textarea id="comment_text" cols="45" rows="8"
											maxlength="65525" aria-required="true"
											class="layui-textarea fly-editor"></textarea>
										<input type="hidden" name="token" id="token"
											value="5902fb48210575fd7438b42acf8634bb">
									</p>
									<p class="form-submit">
										<input type="button" onclick="submit_comment()" id="submit"
											class="submit-btn" value="发表评论">
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="site-footer clearfix u-textAlignCenter footer-link ">
		友情链接： 
		<span class="layui-breadcrumb" lay-separator="|" style="visibility: visible;"> 
			<a href="" target="_blank">See You</a> 
			<a href="" target="_blank">宇宙大人博客</a> 
			<a href="" target="_blank">面糊棉花麻花</a> 
		</span>
	</div>
	
	<%@ include file="footer.jsp" %>
	
	<script>
	layui.use('element', function(){
	  var element = layui.element;
	});
	</script>
</body>
</html>