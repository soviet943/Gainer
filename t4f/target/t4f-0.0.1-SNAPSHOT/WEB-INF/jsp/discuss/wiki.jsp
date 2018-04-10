<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<html>
<head>
<base href="<%=basePath%>"> 
<link href="images/pic/csgo.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>T4F 电竞资讯</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/index.css" type="text/css">
<link rel="stylesheet" href="css/wiki.css" type="text/css">
<link rel="stylesheet" href="css/discuss.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/wiki.js"></script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="container">
	<div class="mall-detail-left">
		<div id="carousel" class="carousel slide mb-10">
			<!-- 轮播-->
		    <div class="carousel-inner">
		        <div class="item active">
		            <a href="" target="_blank" id="wiki-link">
		            	<img src="https://i.c5game.com/image/u-140961345a7d32d4c84df.jpg">
		            </a>
		        </div>
		        <div class="item">
		            <a href="" target="_blank" id="wiki-link">
		            	<img src="https://i.c5game.com/image/u-140961345a794d092ee1c.jpg">
		            </a>
		        </div>
		    </div>
		    <a class="carousel-control left" style="line-height: 292px;width: 8%;" href="#carousel" data-slide="prev">
		    	<span class="iconfont ft-22">&#xe78d;</span>
		    </a>
		    <a class="carousel-control right" style="line-height: 292px;width: 8%;" href="#carousel" data-slide="next">
		    	<span class="iconfont ft-22">&#xe655;</span>
		    </a>
		</div>
		
		<div class="wiki-top mt-10">
			<div class="wiki-swiper">
				<a href="" target="_blank">
					<img src="https://i.c5game.com/image/u-140961345a7d45119c7dc.jpg@248w.png" width="261" height="156">
					<span class="text-center ft-white">CS:GO地图中纪念比赛惊人瞬间的涂鸦-飞翔的AWP</span>
				</a>
			</div>
			<div class="wiki-swiper">
				<a href="" target="_blank">
					<img src="https://i.c5game.com/image/u-140961345a66d689471b7.jpg" width="261" height="156">
					<span class="text-center ft-white">C5GAME唯一经验水贴处</span>
				</a>
			</div>
			<div class="wiki-swiper">
				<a href="" target="_blank" style="margin-right: 0px;">
					<img src="https://i.c5game.com/image/u-140961345a795e73bc3cd.jpg@248w.png" width="261" height="156">
					<span class="text-center ft-white">写一个关于C5GAME怎么升级的经验帖</span>
				</a>
			</div>
		</div>
		<!-- 胶囊导航 -->
		<div class="wiki-content mt-20">
			<ul id="hole-fixed-bar" class="floor-nav" role="tablist">
				<li class="active" role="presentation">
					<a href="#all" role="tab" data-toggle="tab">
						全部
					</a>
				</li>
				<li class="" role="presentation">
					<a href="#csgo" role="tab" data-toggle="tab">
						<span class="icon-csgo mr-5"></span>
						CS:GO
					</a>
				</li>
				<li class="" role="presentation">
					<a href="#dota2" role="tab" data-toggle="tab">
						<span class="icon-dota2 mr-5"></span>
						DOTA2
					</a>
				</li>
				<li class="" role="presentation">
					<a href="#pubg" role="tab" data-toggle="tab">
						<span class="icon-pubg mr-5"></span>
						绝地求生
					</a>
				</li>
				<li class="" role="presentation">
					<a href="#h1z1" role="tab" data-toggle="tab">
						<span class="icon-h1z1 mr-5"></span>
						H1Z1:KOTK
					</a>
				</li>
			</ul>
		</div>
		<!-- 胶囊导航对应的界面 -->
		<!-- csgo资讯 -->
		<div class="tab-content">
		
		</div>
		
	</div>
	
	<div class="detail-right">
		<div class="user-logined">
			<div class="region-head">
				<img class="img-circle" src="https://i.c5game.com/avatar.png@100w.png" id="user-pic">
				<div class="text-center mt-5" style="line-height: 25px;">
					<span class="name-ellipsis-160">${user }</span>
				</div>
			</div>
			<div class="text-center mt-60">
				<a target="_blank" href="discuss/myarticle" class="btn btn-blue" style="height: 34px;border-radius: 0;line-height:32px;width: 115px;margin-right: 16px;">我的文章</a>
				<a target="_blank" href="discuss/submission" class="btn btn-green" style="height: 34px;border-radius: 0;line-height:32px;width: 115px;">点击投稿</a>
			</div>
		</div>
	</div>
</div>
<%@include file="../common/foot.jsp" %>
<%@include file="../common/modal.jsp" %>

</body>
</html>