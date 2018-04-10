<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
<link href="images/pic/csgo.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>T4F 饰品商城 | CS:GO饰品</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/csgo.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/csgo.js"></script>
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<%@include file="csnav.jsp" %>
	<div class="csgo-container">
<!-- 左边筛选部分====================================== -->
		<div class="search-left">
			<div class="filter-cat-group">
				<div class="csgo_label">筛选项</div>
				<div class="filter-display">
					<a href="csgo">重置&nbsp;&nbsp;&nbsp;</a>
				</div>
			</div>
			<div class="filter-group">
				<div id="csgo_label">外观</div>
				<div class="filter-list" id="filter-exterior">
					<a class="filter-parameter">不限</a>
					<a class="filter-parameter">崭新出厂</a>
					<a class="filter-parameter">略有磨损</a>
					<a class="filter-parameter">久经沙场</a>
					<a class="filter-parameter">破损不堪</a>
					<a class="filter-parameter">战痕累累</a>
				</div>
			</div>
			<div class="filter-group">
				<div class="csgo_label">品质</div>
				<div class="filter-list" id="filter-quality">
					<a class="filter-parameter">不限</a>
					<a class="filter-parameter">普通</a>
					<a class="filter-parameter" style="color: #cf6a32;">StatTrak™</a>
					<a class="filter-parameter" style="color: #8650AC;">纪念品</a>
					<a class="filter-parameter" style="color: #8650AC;">★</a>
				</div>
			</div>
			<div class="filter-group">
				<div class="csgo_label">稀有度</div>
				<div class="filter-list" id="filter-rarity">
					<a class="filter-parameter">不限</a>
					<a class="filter-parameter" style="color: #b0c3d9;">普通级</a>
					<a class="filter-parameter" style="color: #4b69ff;">军舰级</a>
					<a class="filter-parameter" style="color: #5e98d9;">工业级</a>
					<a class="filter-parameter" style="color: #8847ff;">受限</a>
					<a class="filter-parameter" style="color: #d32ce6;">保密</a>
					<a class="filter-parameter" style="color: #afb0b2;">隐秘</a>
					<a class="filter-parameter" style="color: #afb0b2;">违禁</a>
					<a class="filter-parameter" style="color: #e4ae39;">保密</a>
				</div>
			</div>
			<!-- 热卖排行================================ -->
			<div class="floor-nav-title">热卖排行</div>
			<div class="floor-content">
				<ul class="side-list">
				
				</ul>
			</div>
		</div>
<!-- 右面物品展示部分===================================== -->
		<div class="search-right">
			<!-- 价格筛选部分 -->
			<ul class="search-bar">
				<li id="orderByTime">
					<a>
						按时间
						<span id="time-asc" class="glyphicon glyphicon-arrow-up" style="display: none;"></span>
						<span id="time-desc" class="glyphicon glyphicon-arrow-down" style="display: none;"></span>
					</a>
				</li>
				<li id="orderByPrice">
					<a>
						按价格
						<span id="price-asc" class="glyphicon glyphicon-arrow-up" style="display: none;"></span>
						<span id="price-desc" class="glyphicon glyphicon-arrow-down" style="display: none;"></span>
					</a>
				</li>
				<li class="sort-price">
					<input class="value-input" id="min" value="${filter.min }" name="min" placeholder="￥" type="text">
					<span style="margin:0 5px;">-</span>
					<input class="value-input" id="max" value="${filter.max }" name="max" placeholder="￥" type="text">
					<button type="submit" class="btn btn-blue" onclick="submit_input()" style="margin-left: 5px;">过滤</button>
				</li>
				<li class="pull-right">
					<div class="csgo-searchbox">
						<input style="width:250px;" id="item_name" name="item_name" value="${filter.item_name }" type="text">
						<span id="search-submit" class="glyphicon glyphicon-search"></span>
					</div>
				</li>
			</ul>
			<input name="type" id="type" value="${filter.type }" type="hidden">
			<input name="item_type" id="item_type" value="${filter.item_type }" type="hidden">
			<input name="exterior" id="exterior" value="${filter.exterior }" type="hidden">
			<input name="quality" id="quality" value="${filter.quality }" type="hidden">
			<input name="rarity" id="rarity" value="${filter.rarity }" type="hidden">
			<input name="update_time" id="update_time" value="${filter.update_time }" type="hidden">
			<input name="price" id="price" value="${filter.price }" type="hidden">
			<input name="page" id="page" value="${filter.page }" type="hidden">
			<input name="pages" id="pages" value="${result.data.page.pages }" type="hidden">
			<input name="current" id="current" value="${result.data.page.current }" type="hidden">
			<!-- 饰品展示部分(4 X 7) -->
			<div class="tab-content">
				<ul class="list-item4">
				<c:forEach items="${result.data.csgoitem }" var="each_item">
					<li class="selling">
						<a class="csgo-img-bg" href="csgo/item/${each_item.id}">
							<img class="item-img" alt="${each_item.item_name}" src="http://120.78.164.143:80/images/csitem/${each_item.img}.png">
							<span class="rarity-box">${each_item.exterior}</span>
						</a>
						<p class="name">
							<a href="csgo/item/${each_item.id}">
								<span class="text-unique">${each_item.item_name}</span>
							</a>
						</p>
						<p class="info">
							售价
							<span id="price_info">${each_item.price}</span>
							起
						</p>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!-- 饰品显示分页 -->
			<div class="yema divsetcenter">
				<ul id="paging" class="pagination">
	
				</ul>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	<%@include file="../common/foot.jsp" %>
	<%@include file="../common/modal.jsp" %>
</body>
</html>