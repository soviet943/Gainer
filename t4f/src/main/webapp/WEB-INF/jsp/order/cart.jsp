<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>T4F 饰品商城 | 购物车</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/csgo.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/cart.js"></script>
<link rel="stylesheet" href="css/order.css" type="text/css">
<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" type="text/css">
<link rel="stylesheet" href="https://cdn.bootcss.com/awesome-bootstrap-checkbox/1.0.0/awesome-bootstrap-checkbox.css" type="text/css">

<style type="text/css">
.cart {
	background-color: #1B1F2A;
	margin: 120px 240px;
	min-height: 100px;
}
.cart-table {
	padding-top: 0px;
}
.ml-5 {
	margin-left: 20px;
}
.orange {
	color: #FF9400;
	font-size: 16px;
}
tr:nth-child(even) {
	background-color: #2C3040;
}
td {
	padding-bottom: 0px !important;
	padding-top: 0px !important;
	vertical-align: middle !important;
}
.cartitem-img-bg {
    display: block;
    overflow: hidden;
    background: url("../images/pic/item-bg.jpg") center 0 no-repeat;
    width: 96px;
    height: 64px;
    text-align: center;
}
th {
	text-align: center;
}
td {
	text-align: center;
}
#item_name {
	text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
}
</style>
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<div class="cart">
		<div class="cart-table">
			<table class="table col-md-8">
				<thead style="background-color: #2C3040;">
					<tr>
						<th class="abc-checkbox abc-checkbox-info"><input id="selectAll" type="checkbox" checked="checked"><label for="selectAll"> 全/不选</label></th>
						<th class="text-center">饰品图片</th>
						<th>饰品名称</th>
						<th>游戏名称</th>
						<th>金额</th>
						<th>创建时间</th>
						<th style="padding-right: 10px;">操作</th>
					</tr>
				</thead>
				<tbody id="iteminfo">
				
				</tbody>
			</table>
		</div>
		<div style="clear: both;"></div>
		<div style="margin-right: 30px;margin-top: -10px;">
			<a id="delete_cartitems" class="pull-left btn btn-danger btn-sm" style="margin-left: 15px;">删除</a>
			<span class="btn btn-orange pull-right ml-5" id="submit_cart">结算</span>
			<span class="pull-right ml-5">合计: <span class="orange" id="total"></span></span>
			<span class="pull-right ml-5">已选饰品<span class="orange" id="selectedNum"></span>件</span>
		</div>
	</div>
	<div style="height: 250px;"></div>
	<%@include file="../common/foot.jsp" %>
	<%@include file="../common/modal.jsp" %>
	<!-- 模态框 确认删除吗 -->
	<div class="modal fade" id="delete_cartitem_modal">
	    <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content" style="border:1px solid #576170;">
	        	<div class="modal-body" style="background-color: #2c3040; margin:20px;">
	        		<div class="text-center">
	        			<p style="margin-top: 10px;font-size: 14px;">
	        			 	确认要删除吗？
	        			</p>
	        		</div>
	        		<div style="margin-top: 30px; margin-left:270px;">
		        		<a class="btn btn-success" onclick="submit_delete_cartitem();">确认</a>
		        		<a class="btn btn-warning" style="margin-left: 20px;" data-dismiss="modal">取消</a>
		        	</div>
	        	</div>
	        </div>
	    </div>
	</div>
	<!-- 模态框 确认删除这些吗 -->
	<div class="modal fade" id="delete_cartitems_modal">
	    <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content" style="border:1px solid #576170;">
	        	<div class="modal-body" style="background-color: #2c3040; margin:20px;">
	        		<div class="text-center">
	        			<p style="margin-top: 10px;font-size: 14px;">
	        			 	确认要删除这 <span id="delete_Num" style="color: #f40;"></span> 项吗?
	        			</p>
	        		</div>
	        		<div style="margin-top: 30px; margin-left:270px;">
		        		<a class="btn btn-success" onclick="submit_delete_cartitems();">确认</a>
		        		<a class="btn btn-warning" style="margin-left: 20px;" data-dismiss="modal">取消</a>
		        	</div>
	        	</div>
	        </div>
	    </div>
	</div>
</body>
</html>