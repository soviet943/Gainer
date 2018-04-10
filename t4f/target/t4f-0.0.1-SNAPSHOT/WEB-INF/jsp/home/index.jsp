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
<title>T4F 饰品商城 | 主页</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/index.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="container">
	<div id="slide-carousel">
	    <div class="banner carousel slide" data-ride="carousel" id="carousel">
		    <ol class="carousel-indicators">
				<li data-target="#carousel" data-slide-to="0" class="active"></li>
				<li data-target="#carousel" data-slide-to="1"></li>
				<li data-target="#carousel" data-slide-to="2"></li>
				<li data-target="#carousel" data-slide-to="3"></li>
				<li data-target="#carousel" data-slide-to="4"></li>
			</ol>
		    <div class="carousel-inner" role="listbox">
				<div class="item active">
		            <a href="" target="_blank">
		                <img src="https://i.c5game.com/image/u-8876215a37ee7877728.jpg@830w.png" width="830" height="300" />
		            </a>
		        </div>
		        <div class="item">
		            <a href="" target="_blank">
		                <img src="https://i.c5game.com/image/u-140961345a376ac2a0453.jpg@830w.png" width="830" height="300" />
		            </a>
		        </div>
		        <div class="item">
		            <a href="" target="_blank">
		                <img src="https://i.c5game.com/image/u-1087825a3135e982892.jpg@830w.png" width="830" height="300" />
		            </a>
		        </div>
		        <div class="item">
		            <a href="" target="_blank">
		                <img src="https://i.c5game.com/image/u-1409613459f822f7e90ac.jpg@830w.png" width="830" height="300" />
		            </a>
		        </div>
		        <div class="item">
		            <a href="" target="_blank">
		                <img src="https://i.c5game.com/image/u-140961345a213d6554a8d.jpg@830w.png" width="830" height="300" />
		            </a>
		        </div>
		 	</div>
		</div>  
	</div>
	<c:choose>
		<c:when test="${user == null}">
			<div class="user_unlogin">
				<a class="gotoLogin" data-toggle="modal" data-target="#login_modal">登 录</a>
				<a class="gotoReg" data-toggle="modal" data-target="#register_modal">注 册</a>
			</div>
       	</c:when>
       	<c:otherwise>
       		<div class="user_welcome">
       			<div class="user_logined">
       				<div class="region-head">
       					<!-- 签到 -->
       					<a class="sign-in active" onclick="signin();"></a>
       					<!-- 用户头像 -->
       					<span class="user-pic">
       						<img class="img-circle" src="https://i.c5game.com/avatar.png@100w.png">
       					</span>
       					<!-- 用户名称 -->
       					<div class="text-center welcome-username">
       						<span class="name-ellipsis-185 ft-white">${user }</span>
       						<span class="user-level level-2"></span>
       					</div>
       				</div>
	       			<div class="region-count">
	       				<div class="user-account-info user-balance">
	       				 	<span>账户余额</span>
	       				 	<span class="ft-18 ft-orange">￥${money }</span>
	       				</div>
	       				<div class="user-account-info user-interation">
	       					<span>积分</span>
	       					<span class="ft-18 ft-yellow">20</span>
	       				</div>
	       			</div>
       			</div>
			</div>
       	</c:otherwise>
	</c:choose>
	<div style="clear: both;"></div>
	
	<!-- 饰品推荐（从每种游戏类型随机抽取10个） -->
	<div class="floor">
		<!-- 胶囊式导航栏 -->
		<ul class="floor-nav" role="tablist">
			<li class="">
				<span style="padding-bottom: 8px;font-style: italic;font-weight: bold;">饰品推荐</span>
			</li>
			<li class="active" role="presentation">
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
		<!-- 导航栏对应的窗口内容 -->
		<div class="tab-content">
			<!-- csgo推荐 -->
			<div id="csgo" class="tab-pane active" role="tabpanel">
				<ul class="clearfix list-item5">
				<c:forEach items="${random_csgo.data }" var="each_item">
					<li>
						<a class="img csgo-img-bg text-center" target="_blank" href="csgo/item/${each_item.id}">
							<img src="http://120.78.164.143:80/images/csitem/${each_item.img}.png" style="max-width: 100%;height: 127px;">
						</a>
						<p class="name">
							<a href="">${each_item.item_name}</a>
						</p>
						<p class="info">
							<span class="pull-left">
								售价
								<span class="price">${each_item.price}</span>
							</span>
							<span class="num">${each_item.sales}件在售</span>
						</p>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!-- dota2推荐 -->
			<div id="dota2" class="tab-pane" role="tabpanel">
				<ul class="clearfix list-item5">
				<c:forEach items="${random_dota2.data }" var="each_item">
					<li>
						<a class="img csgo-img-bg text-center" target="_blank" href="dota2/item/${each_item.id}">
							<img src="http://120.78.164.143:80/images/dota2_item/${each_item.img}.png" style="max-width: 100%;height: 127px;">
						</a>
						<p class="name">
							<a href="">${each_item.item_name}</a>
						</p>
						<p class="info">
							<span class="pull-left">
								售价
								<span class="price">${each_item.price}</span>
							</span>
							<span class="num">${each_item.sales}件在售</span>
						</p>
					</li>
				</c:forEach>
				</ul>
			</div>
			
		</div>
	</div>    
	
	<!-- 土豪专区 -->
	<div class="floor1">
		<!-- 胶囊式导航栏 -->
		<ul class="floor-nav" role="tablist">
			<li class="">
				<span style="padding-bottom: 8px;font-style: italic;font-weight: bold;">土豪推荐</span>
			</li>
			<li class="active" role="presentation">
				<a href="#csgo_rich" role="tab" data-toggle="tab">
					<span class="icon-csgo mr-5"></span>
					CS:GO
				</a>
			</li>
			<li class="" role="presentation">
				<a href="#dota2_rich" role="tab" data-toggle="tab">
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
		<!-- 导航栏对应的窗口内容 -->
		<div class="tab-content">
			<!-- csgo土豪推荐 -->
			<div id="csgo_rich" class="tab-pane active" role="tabpanel">
				<ul class="clearfix list-item5">
				<c:forEach items="${random_csgo_rich.data }" var="each_item">
					<li class="floor1-redpanel">
						<a class="img csgo-img-bg text-center" target="_blank" href="csgo/item/${each_item.id}">
							<img src="http://120.78.164.143:80/images/csitem/${each_item.img}.png" style="max-width: 100%;height: 127px;">
						</a>
						<p class="name">
							<a href="">${each_item.item_name}</a>
						</p>
						<p class="info">
							<span class="pull-left">
								售价
								<span class="price">${each_item.price}</span>
							</span>
							<span class="num">${each_item.sales}件在售</span>
						</p>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!-- dota2土豪推荐 -->
			<div id="dota2_rich" class="tab-pane" role="tabpanel">
				<ul class="clearfix list-item5">
				<c:forEach items="${random_dota2_rich.data }" var="each_item">
					<li class="floor1-redpanel">
						<a class="img csgo-img-bg text-center" target="_blank" href="dota2/item/${each_item.id}">
							<img src="http://120.78.164.143:80/images/dota2_item/${each_item.img}.png" style="max-width: 100%;height: 127px;">
						</a>
						<p class="name">
							<a href="">${each_item.item_name}</a>
						</p>
						<p class="info">
							<span class="pull-left">
								售价
								<span class="price">${each_item.price}</span>
							</span>
							<span class="num">${each_item.sales}件在售</span>
						</p>
					</li>
				</c:forEach>
				</ul>
			</div>
			
		</div>
	</div>    
	
</div>

<%@include file="../common/foot.jsp" %>
<%@include file="../common/modal.jsp" %>
<script>
	//轮播循环用
	$(document).ready(function () {
		$('#carousel').carousel({
		    interval: 5000
		});
	});

</script>
</body>
</html>