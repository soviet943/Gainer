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
<title>T4F 饰品商城 | CSGO饰品</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/csgo.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/csgo.js"></script>
<link rel="stylesheet" href="css/item.css" type="text/css">
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<div class="container" style="margin-top: 120px;">
		<div class="detail-left">
			<div class="sale-item">
				<div class="sale-item-bg">
					<img id="item_img" src="http://120.78.164.143:80/images/csitem/${item.data.img }.png" style="max-width: 100%" height="208">
				</div>
				<div class="sale-item-info">
					<div class="attr_name">
						<span style="width: 365px;display: inline-block;overflow: hidden;">${item.data.item_name }</span>
					</div>
					<div class="attr_type">
						<span class="icon-csgo"></span>
						<span class="text-unique">${item.data.quality }</span>
						<span>${item.data.rarity }</span>
						<span>${item.data.type }</span>
					</div>
					<div class="attr_price">
						<span>参考价: 约￥<c:out value="${item.data.price }"></c:out></span>
					</div>
					<hr style="border-top:1px solid #2E3547; margin-top:40px" noshade="noshade">
					<ul class="sale-item-bottom">
						<li class="pull-left">
							累计出售：
							<span style="color:#d2d2d2"><c:out value="${item.data.sales }"></c:out></span>
							件
						</li>
						<li class="pull-right" style="width: 112px;">
                        	<c:set value="${item.data.price -money}" var="need"/>  
							<c:choose>
		                        <c:when test="${user == null}">
		                            <button class="btn btn-orange" data-toggle="modal" data-target="#login_modal">加入购物车</button>
		                        </c:when>
		                        <c:when test="${user != null}">
		                        	<c:if test="${need > 0 }">
		                        		<button class="btn btn-orange" data-toggle="modal" data-target="#need_money_modal">加入购物车</button>
		                        	</c:if>
		                        	<c:if test="${need <= 0 }">
		                        		<button class="btn btn-orange" onclick="addToCart('csgo_${item.data.id }')">加入购物车</button>
		                        	</c:if>
		                        </c:when>
		                    </c:choose>
			            </li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../common/foot.jsp" %>
	<%@include file="../common/modal.jsp" %>
</body>
</html>