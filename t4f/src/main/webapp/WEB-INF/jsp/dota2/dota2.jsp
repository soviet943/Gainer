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
<title>T4F 饰品商城 | DOTA2饰品</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> 
<link rel="stylesheet" href="css/head.css" type="text/css">
<link rel="stylesheet" href="css/dota2.css" type="text/css">
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/dota2.js"></script>
</head>
<body>
	<%@include file="../common/head.jsp" %>
	<div class="container">
<!-- 左边筛选部分====================================== -->
		<div class="search-left">
			<div class="filter-cat-group">
				<div id="label">筛选项</div>
				<div class="filter-display">
					<a href="dota2">重置&nbsp;&nbsp;&nbsp;</a>
				</div>
			</div>
			<div class="filter-group">
				<div id="label">稀有度</div>
				<div class="filter-list" id="filter-rarity">
					<a class="filter-parameter">不限</a>
					<a class="filter-parameter" style="color: #b0c3d9;">普通</a>
					<a class="filter-parameter" style="color: #5e98d9;">罕见</a>
					<a class="filter-parameter" style="color: #4b69ff;">稀有</a>
					<a class="filter-parameter" style="color: #8847ff;">神话</a>
					<a class="filter-parameter" style="color: #e4ae39;">不朽</a>
					<a class="filter-parameter" style="color: #AFB0B2;">远古</a>
					<a class="filter-parameter" style="color: #d32ce6;">传说</a>
					<a class="filter-parameter" style="color: #ade55c;">至宝</a>
				</div>
			</div>
			<div class="filter-group">
				<div id="label">品质</div>
				<div class="filter-list" id="filter-quality">
					<a class="filter-parameter">不限</a>
					<a class="filter-parameter" style="color: #B2B2B2;">基础</a>
					<a class="filter-parameter" style="color: #4d7455;">纯正</a>
					<a class="filter-parameter" style="color: #476291;">上古</a>
					<a class="filter-parameter" style="color: #8650AC;">独特</a>
					<a class="filter-parameter" style="color: #d2d2d2;">标准</a>
					<a class="filter-parameter" style="color: #70B04A;">社区</a>
					<a class="filter-parameter" style="color: #A50F79;">Valve</a>
					<a class="filter-parameter" style="color: #70B04A;">自制</a>
					<a class="filter-parameter" style="color: #476291;">自定义</a>
					<a class="filter-parameter" style="color: #cf6a32;">铭刻</a>
					<a class="filter-parameter" style="color: #b2b2b2;">完整</a>
					<a class="filter-parameter" style="color: #38F3AB;">凶煞</a>
					<a class="filter-parameter" style="color: #8650AC;">英雄传世</a>
					<a class="filter-parameter" style="color: #FF0;">青睐</a>
					<a class="filter-parameter" style="color: #EB4B4B;">传奇</a>
					<a class="filter-parameter" style="color: #ADE55C;">亲笔签名</a>
					<a class="filter-parameter" style="color: #fff;">绝版</a>
					<a class="filter-parameter" style="color: #d1d1d1;">尊享</a>
					<a class="filter-parameter" style="color: #4682b4;">冻人</a>
					<a class="filter-parameter" style="color: #A32C2E;">冥灵</a>
					<a class="filter-parameter" style="color: #32cd32;">吉祥</a>
					<a class="filter-parameter" style="color: #32cd32;">融合</a>
				</div>
			</div>
			<div class="filter-group">
				<div id="label">种类</div>
				<div class="filter-list" id="filter-type">
					<a class="filter-parameter">不限</a>
					<a class="filter-parameter">宝石/符文</a>
					<a class="filter-parameter">表情工具包</a>
					<a class="filter-parameter">播音员</a>
					<a class="filter-parameter">嘲讽</a>
					<a class="filter-parameter">符印</a>
					<a class="filter-parameter">工具</a>
					<a class="filter-parameter">过期珍藏</a>
					<a class="filter-parameter">基础</a>
					<a class="filter-parameter">锦旗</a>
					<a class="filter-parameter">可佩带</a>
					<a class="filter-parameter">捆绑包</a>
					<a class="filter-parameter">联赛</a>
					<a class="filter-parameter">图纸</a>
					<a class="filter-parameter">信使</a>
					<a class="filter-parameter">音乐</a>
					<a class="filter-parameter">游戏界面皮肤</a>
					<a class="filter-parameter">杂项</a>
					<a class="filter-parameter">载入画面</a>
					<a class="filter-parameter">珍藏</a>
					<a class="filter-parameter">指针包</a>
					<a class="filter-parameter">守卫</a>
				</div>
			</div>
			<div class="filter-group">
				<c:set var="pic_dir" value="http://120.78.164.143:80/images/dota2_hero/"></c:set>
				<div id="label">力量</div>
				<div class="filter-hero" id="filter-hero">
					<img class="mask" src="${pic_dir }斧王.png" alt="斧王">
					<img class="mask" src="${pic_dir }撼地者.png" alt="撼地者">
					<img class="mask" src="${pic_dir }帕吉.png" alt="帕吉">
					<img class="mask" src="${pic_dir }沙王.png" alt="沙王">
					<img class="mask" src="${pic_dir }斯温.png" alt="斯温">
					<img class="mask" src="${pic_dir }小小.png" alt="小小">
					<img class="mask" src="${pic_dir }昆卡.png" alt="昆卡">
					<img class="mask" src="${pic_dir }斯拉达.png" alt="斯拉达">
					<img class="mask" src="${pic_dir }潮汐猎人.png" alt="潮汐猎人">
					<img class="mask" src="${pic_dir }兽王.png" alt="兽王">
					<img class="mask" src="${pic_dir }冥魂大帝.png" alt="冥魂大帝">
					<img class="mask" src="${pic_dir }龙骑士.png" alt="龙骑士">
					<img class="mask" src="${pic_dir }发条技师.png" alt="发条技师">
					<img class="mask" src="${pic_dir }噬魂鬼.png" alt="噬魂鬼">
					<img class="mask" src="${pic_dir }全能骑士.png" alt="全能骑士">
					<img class="mask" src="${pic_dir }哈斯卡.png" alt="哈斯卡">
					<img class="mask" src="${pic_dir }暗夜魔王.png" alt="暗夜魔王">
					<img class="mask" src="${pic_dir }末日使者.png" alt="末日使者">
					<img class="mask" src="${pic_dir }裂魂人.png" alt="裂魂人">
					<img class="mask" src="${pic_dir }炼金术士.png" alt="炼金术士">
					<img class="mask" src="${pic_dir }狼人.png" alt="狼人">
					<img class="mask" src="${pic_dir }酒仙.png" alt="酒仙">
					<img class="mask" src="${pic_dir }混沌骑士.png" alt="混沌骑士">
					<img class="mask" src="${pic_dir }树精卫士.png" alt="树精卫士">
					<img class="mask" src="${pic_dir }不朽尸王.png" alt="不朽尸王">
					<img class="mask" src="${pic_dir }艾欧.png" alt="艾欧">
					<img class="mask" src="${pic_dir }半人马战行者.png" alt="半人马战行者">
					<img class="mask" src="${pic_dir }马格纳斯.png" alt="马格纳斯">
					<img class="mask" src="${pic_dir }伐木机.png" alt="伐木机">
					<img class="mask" src="${pic_dir }钢背兽.png" alt="钢背兽">
					<img class="mask" src="${pic_dir }巨牙海民.png" alt="巨牙海民">
					<img class="mask" src="${pic_dir }亚巴顿.png" alt="亚巴顿">
					<img class="mask" src="${pic_dir }上古巨神.png" alt="上古巨神">
					<img class="mask" src="${pic_dir }军团指挥官.png" alt="军团指挥官">
					<img class="mask" src="${pic_dir }大地之灵.png" alt="大地之灵">
					<img class="mask" src="${pic_dir }孽主.png" alt="孽主">
					<img class="mask" src="${pic_dir }凤凰.png" alt="凤凰">
				</div>
				
				<div id="label">敏捷</div>
				<div class="filter-hero" id="filter-hero">
					<img class="mask" src="${pic_dir }敌法师.png" alt="敌法师">
					<img class="mask" src="${pic_dir }嗜血狂魔.png" alt="嗜血狂魔">
					<img class="mask" src="${pic_dir }卓尔游侠.png" alt="卓尔游侠">
					<img class="mask" src="${pic_dir }主宰.png" alt="主宰">
					<img class="mask" src="${pic_dir }米拉娜.png" alt="米拉娜">
					<img class="mask" src="${pic_dir }变体精灵.png" alt="变体精灵">
					<img class="mask" src="${pic_dir }影魔.png" alt="影魔">
					<img class="mask" src="${pic_dir }幻影长矛手.png" alt="幻影长矛手">
					<img class="mask" src="${pic_dir }剃刀.png" alt="剃刀">
					<img class="mask" src="${pic_dir }复仇之魂.png" alt="复仇之魂">
					<img class="mask" src="${pic_dir }力丸.png" alt="力丸">
					<img class="mask" src="${pic_dir }狙击手.png" alt="狙击手">
					<img class="mask" src="${pic_dir }剧毒术士.png" alt="剧毒术士">
					<img class="mask" src="${pic_dir }虚空假面.png" alt="虚空假面">
					<img class="mask" src="${pic_dir }幻影刺客.png" alt="幻影刺客">
					<img class="mask" src="${pic_dir }圣堂刺客.png" alt="圣堂刺客">
					<img class="mask" src="${pic_dir }冥界亚龙.png" alt="冥界亚龙">
					<img class="mask" src="${pic_dir }露娜.png" alt="露娜">
					<img class="mask" src="${pic_dir }克林克兹.png" alt="克林克兹">
					<img class="mask" src="${pic_dir }育母蜘蛛.png" alt="育母蜘蛛">
					<img class="mask" src="${pic_dir }赏金猎人.png" alt="赏金猎人">
					<img class="mask" src="${pic_dir }编织者.png" alt="编织者">
					<img class="mask" src="${pic_dir }幽鬼.png" alt="幽鬼">
					<img class="mask" src="${pic_dir }熊战士.png" alt="熊战士">
					<img class="mask" src="${pic_dir }矮人直升机.png" alt="矮人直升机">
					<img class="mask" src="${pic_dir }德鲁伊.png" alt="德鲁伊">
					<img class="mask" src="${pic_dir }米波.png" alt="米波">
					<img class="mask" src="${pic_dir }司夜刺客.png" alt="司夜刺客">
					<img class="mask" src="${pic_dir }娜迦海妖.png" alt="娜迦海妖">
					<img class="mask" src="${pic_dir }斯拉克.png" alt="斯拉克">
					<img class="mask" src="${pic_dir }美杜莎.png" alt="美杜莎">
					<img class="mask" src="${pic_dir }巨魔战将.png" alt="巨魔战将">
					<img class="mask" src="${pic_dir }灰烬之灵.png" alt="灰烬之灵">
					<img class="mask" src="${pic_dir }恐怖利刃.png" alt="恐怖利刃">
					<img class="mask" src="${pic_dir }天穹守望者.png" alt="天穹守望者">
					<img class="mask" src="${pic_dir }齐天大圣.png" alt="齐天大圣">
					<img class="mask" src="${pic_dir }石鳞剑士.png" alt="石鳞剑士">
				</div>
				
				<div id="label">智力</div>
				<div class="filter-hero" id="filter-hero">
					<img class="mask" src="${pic_dir }祸乱之源.png" alt="祸乱之源">
					<img class="mask" src="${pic_dir }水晶室女.png" alt="水晶室女">
					<img class="mask" src="${pic_dir }帕克.png" alt="帕克">
					<img class="mask" src="${pic_dir }风暴之灵.png" alt="风暴之灵">
					<img class="mask" src="${pic_dir }风行者.png" alt="风行者">
					<img class="mask" src="${pic_dir }宙斯.png" alt="宙斯">
					<img class="mask" src="${pic_dir }莉娜.png" alt="莉娜">
					<img class="mask" src="${pic_dir }莱恩.png" alt="莱恩">
					<img class="mask" src="${pic_dir }暗影萨满.png" alt="暗影萨满">
					<img class="mask" src="${pic_dir }巫医.png" alt="巫医">
					<img class="mask" src="${pic_dir }巫妖.png" alt="巫妖">
					<img class="mask" src="${pic_dir }谜团.png" alt="谜团">
					<img class="mask" src="${pic_dir }修补匠.png" alt="修补匠">
					<img class="mask" src="${pic_dir }瘟疫法师.png" alt="瘟疫法师">
					<img class="mask" src="${pic_dir }术士.png" alt="术士">
					<img class="mask" src="${pic_dir }痛苦女王.png" alt="痛苦女王">
					<img class="mask" src="${pic_dir }死亡先知.png" alt="死亡先知">
					<img class="mask" src="${pic_dir }帕格纳.png" alt="帕格纳">
					<img class="mask" src="${pic_dir }戴泽.png" alt="戴泽">
					<img class="mask" src="${pic_dir }拉席克.png" alt="拉席克">
					<img class="mask" src="${pic_dir }先知.png" alt="先知">
					<img class="mask" src="${pic_dir }黑暗贤者.png" alt="黑暗贤者">
					<img class="mask" src="${pic_dir }魅惑魔女.png" alt="魅惑魔女">
					<img class="mask" src="${pic_dir }杰奇洛.png" alt="杰奇洛">
					<img class="mask" src="${pic_dir }蝙蝠骑士.png" alt="蝙蝠骑士">
					<img class="mask" src="${pic_dir }陈.png" alt="陈">
					<img class="mask" src="${pic_dir }远古冰魄.png" alt="远古冰魄">
					<img class="mask" src="${pic_dir }祈求者.png" alt="祈求者">
					<img class="mask" src="${pic_dir }沉默术士.png" alt="沉默术士">
					<img class="mask" src="${pic_dir }殁境神蚀者.png" alt="殁境神蚀者">
					<img class="mask" src="${pic_dir }暗影恶魔.png" alt="暗影恶魔">
					<img class="mask" src="${pic_dir }食人魔魔法师.png" alt="食人魔魔法师">
					<img class="mask" src="${pic_dir }拉比克.png" alt="拉比克">
					<img class="mask" src="${pic_dir }干扰者.png" alt="干扰者">
					<img class="mask" src="${pic_dir }光之守卫.png" alt="光之守卫">
					<img class="mask" src="${pic_dir }天怒法师.png" alt="天怒法师">
					<img class="mask" src="${pic_dir }工程师.png" alt="工程师">
					<img class="mask" src="${pic_dir }神谕者.png" alt="神谕者">
					<img class="mask" src="${pic_dir }寒冬飞龙.png" alt="寒冬飞龙">
					<img class="mask" src="${pic_dir }邪影芳灵.png" alt="邪影芳灵">
				</div>
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
					<input class="value-input" id="min" value="" name="min" placeholder="￥" type="text">
					<span style="margin:0 5px;">-</span>
					<input class="value-input" id="max" value="" name="max" placeholder="￥" type="text">
					<button type="submit" class="btn btn-blue" onclick="submit_input()" style="margin-left: 5px;">过滤</button>
				</li>
				<li class="pull-right">
					<div class="searchbox">
						<input style="width:250px;" id="item_name" name="item_name" value="" type="text">
						<span id="search-submit" class="glyphicon glyphicon-search"></span>
					</div>
				</li>
			</ul>
			<input name="rarity" id="rarity" value="${filter.rarity }" type="hidden">
			<input name="quality" id="quality" value="${filter.quality }" type="hidden">
			<input name="type" id="type" value="${filter.type }" type="hidden">
			<input name="hero" id="hero" value="${filter.hero }" type="hidden">
			<input name="update_time" id="update_time" value="${filter.update_time }" type="hidden">
			<input name="price" id="price" value="${filter.price }" type="hidden">
			<input name="page" id="page" value="${filter.page }" type="hidden">
			<input name="pages" id="pages" value="${result.data.page.pages }" type="hidden">
			<input name="current" id="current" value="${result.data.page.current }" type="hidden">
			<!-- 饰品展示部分(4 X 7) -->
			<div class="tab-content">
				<ul class="list-item4">
					<c:forEach items="${result.data.items }" var="each_item">
						<li class="selling">
							<a class="img-bg" href="dota2/item/${each_item.id}">
								<img class="item-img" alt="${each_item.item_name}" src="http://120.78.164.143:80/images/dota2_item/${each_item.img}.png">
							</a>
							<p class="name">
								<a href="dota2/item/${each_item.id }">
									<span class="text-unique">${each_item.item_name}</span>
								</a>
							</p>
							<p class="info">
								售价
								<span id="price">${each_item.price}</span>
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
	</div>
	<%@include file="../common/foot.jsp" %>
	<%@include file="../common/modal.jsp" %>
</body>
</html>