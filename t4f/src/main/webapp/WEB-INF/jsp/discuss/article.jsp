<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<html>
<head>
<base href="<%=basePath%>"> 
<link href="images/pic/csgo.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>T4F 饰品商城 | 文章</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/cropper.css" type="text/css">
<link rel="stylesheet" href="css/article.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/article.js"></script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="container">
	<div class="detail-left">
		<div class="wiki-body">
			<div class="wiki-title">
				<p class="ft-22 ft-white text-left">${result.data.article.title }</p>
				<p class="text-left">作者:${result.data.article.username }&nbsp;&nbsp;&nbsp;
				<fmt:formatDate value="${result.data.article.ctime}" pattern="yyyy年MM月dd日"/>&nbsp;&nbsp;
					<span class="icon iconfont">&#xe638;</span>
					<a href="discuss">
					<c:choose>
						<c:when test="${result.data.article.game_type == 0}"></c:when>
						<c:when test="${result.data.article.game_type == 1}">CSGO</c:when>
						<c:when test="${result.data.article.game_type == 2}">DOTA2</c:when>
						<c:when test="${result.data.article.game_type == 3}">PUBG</c:when>
						<c:when test="${result.data.article.game_type == 4}">H1Z1</c:when>
					</c:choose>
					</a>
					<a href="discuss">
					<c:choose>
						<c:when test="${result.data.article.article_type == 1}">资讯</c:when>
						<c:when test="${result.data.article.article_type == 2}">饰品科普</c:when>
					</c:choose>
					</a>
					&nbsp;&nbsp;来源：T4F&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="icon iconfont">&#xe689;</span>
					${result.data.article.clicked }&nbsp;
					<span class="icon iconfont">&#xe626;</span>
					${result.data.article.haschild }
				</p>
			</div>
			<input type="hidden" id="iflike" value="${like }">
			<input type="hidden" id="aid" value="${result.data.article.aid }">
			<input type="hidden" id="uid" value="${uid }">
			<input type="hidden" id="username" value="${username }">
			<input type="hidden" id="comment_num" value="${result.data.article.haschild }">
			<div class="wiki-pagenation">
				${result.data.article.content }
				<br>
				<div class="text-center">
					<div class="mt-40">
						<button class="zan-btn" onclick="like();">
							<span class="iconfont ft-16">&#xe62e;</span>（<span id="vote_num" class="ft-16">${result.data.article.voted }</span>）
						</button>
					</div>
				</div>
			</div>
			
			<div class="floor-nav floor-nav-title mt-20 ">
            	<span class="glyphicon glyphicon-option-vertical"></span>评论        
            </div>
            <div class="comments-area clearfix">
	            <div class="mb-20">
                	网友评论
	                <span class="pull-right">${result.data.article.haschild }条评论</span>
	            </div>
                <textarea id="content" placeholder="小贴士：嘤~无营养的水帖是获取不到经验的，严重刷屏还会被封禁账号噢~"></textarea>
                <div class="mt-10 clearfix">
                    <a class="btn btn-green pull-right comment-btn" onclick="comment();">发布</a>
                </div>
	            <div class="comment-area clearfix">
	            	<div style="color: #d8d8d8">
		                <img src="https://www.c5game.com/images/wiki/new-comment.png" alt="">
	                	最新评论
            		</div>
            		<ul id="comments" class="clearfix comments-list">
            			
            		</ul>
            		<div class="sale-pagination">
            			<ul id="comment-pagenation" class="pagination">
            				
            			</ul>
            		</div>
	            </div>
	
	        </div>
			
		</div>
		
	</div>
	
	<div class="detail-right">
		<div class="user-logined">
			<div class="region-head">
				<img class="img-circle" src="https://i.c5game.com/avatar.png@100w.png" id="user-pic">
				<div class="text-center mt-5" style="line-height: 25px;">
					<span class="name-ellipsis-160">${result.data.article.username }</span>
				</div>
			</div>
			<div class="text-center mt-60">
				<a target="_blank" href="discuss/select_filter?uid=${result.data.article.uid }" class="btn btn-blue" style="height: 34px;border-radius: 0;line-height:32px;width: 115px;margin-right: 16px;">Ta的文章</a>
				<a target="_blank" href="discuss/submission" class="btn btn-green" style="height: 34px;border-radius: 0;line-height:32px;width: 115px;">关注Ta</a>
			</div>
		</div>
	</div>
	
	
</div>

<%@include file="../common/foot.jsp" %>
<%@include file="../common/modal.jsp" %>
</body>
</html>