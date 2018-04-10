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
<title>T4F 饰品商城 | 我的订单</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/csgo.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/order.js"></script>
<link rel="stylesheet" href="css/order.css" type="text/css">
<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" type="text/css">
<link rel="stylesheet" href="https://cdn.bootcss.com/awesome-bootstrap-checkbox/1.0.0/awesome-bootstrap-checkbox.css" type="text/css">

<style type="text/css">
.myorder {
	background-color: #1B1F2A;
	margin: 120px 260px 0px 260px;
	overflow: auto;
	color: #d1d1d1;
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
tr:nth-child(odd) {
	background-color: #1B1F2A;	
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
	width: 280px;
	text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
}
.table {
	margin-bottom: 0px;
}
#order_time {
	padding-left: 20px;
}
#order_num {
	padding-left: 30px;
}
#order_delete {
	line-height: 35px;
	padding-right: 20px;
}
#order_sum {
	padding-right: 30px;
}
.myorder-head {
	overflow: visible;
}
.order-header {
	background-color: #151A23;
}
#game_type {
	width: 10%;
}
#item_price {
	width: 10%;
}
#item_tr:hover {
	color: #e4b35a;
}
.order-table {
	width: 750px;
	margin: 10px auto 10px auto;
	background-color: #232836;
	line-height: 35px;
	display: table;
	border-top: 1px solid #51432c;
	border-left: 1px solid #51432c;
	border-right: 1px solid #51432c;
}
.order-table:hover {
	border: 1px solid #afb0b2;
}
.myorder-head {
	background-color: #151A23;
	padding: 6px;
}
.myorder-head span {
	font-size: 16px;
}
tbody {
	border: 0px !important;
}
td {
	border-bottom: 1px solid #51432c !important;
	border-top: 1px solid #51432c !important;
}
</style>
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<div class="myorder">
		<div class="myorder-head">
			<span style="padding-left: 40px;">饰品预览</span>
			<span style="padding-left: 140px;">饰品名称</span>
			<span style="padding-left: 110px;">游戏名称</span>
			<span style="padding-left: 20px;">实付款</span>
			<span style="padding-left: 60px;">交易时间</span>
			<span style="padding-left: 30px;">交易操作</span>
		</div>
		<div class="order-tables">
		</div>
	</div>
	<%@include file="../common/foot.jsp" %>
	<%@include file="../common/modal.jsp" %>
</body>
</html>