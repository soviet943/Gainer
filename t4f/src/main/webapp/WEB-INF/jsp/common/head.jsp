<%@page pageEncoding="UTF-8"%>
	<div id="header" style="z-index: 888">
		<div class="logo">
			<a href="">
				<span>
					<img alt="主页" src="images/pic/logo.png">
				</span>
			</a>
		</div>
		<div id="site-nav-state">
			<div id="login-cpan1" class="user-info">
				<div class="user-top">
    				<c:choose>
                        <c:when test="${user == null}">
                            <a data-toggle="modal" data-target="#login_modal">登录</a>
    						<a data-toggle="modal" data-target="#register_modal" class="pl-15">注册用户</a>
                        </c:when>
                        <c:otherwise>
                           	<a href="user/personal">欢迎您：${user} (￥${money})</a>
                           	<c:if test="${cartNum != 0}">
                     			<span id="cart-icon" class="glyphicon glyphicon-shopping-cart pl-15 red-cart"></span> <a href="order/cart">购物车</a> <span class="CarNum">${cartNum }</span>
                           	</c:if>
                           	<c:if test="${cartNum == 0}">
                           		<span id="cart-icon" class="glyphicon glyphicon-shopping-cart pl-15"></span> <a href="order/cart">购物车</a> <span class="CarNum"></span>
                           	</c:if>
                           	<a class="pl-15" onclick="outlogin();">退出</a>
                        </c:otherwise>
                    </c:choose>
				</div>
			</div>
		</div>
		<div id="site-nav-game">
			<ul class="nav-links">
				<li class="">
					<a href="">首页</a>
				</li>
				<li class="">
					<a href="csgo">CS:GO</a>
				</li>
				<li class="">
					<a href="dota2">DOTA2</a>
				</li>
				<li class="">
					<a href="/">H1Z1</a>
				</li>
				<li class="">
					<a href="/">绝地求生</a>
				</li>
				<li class="">
					<a href="/">Steam卡牌</a>
				</li>
				<li class="">
					<a href="/">军团要塞</a>
				</li>
				<li class="">
					<a href="/">收获日2</a>
				</li>
				<li class="">
					<a href="/">Rust</a>
				</li>
				<li class="">
					<a href="/">Unturned</a>
				</li>
				<li class="">
					<a href="/">KF2</a>
				</li>
				<li class="">
					<a href="discuss">资讯</a>
				</li>
				<li class="">
					<a href="/">个人中心</a>
				</li>
			</ul>            
		</div>
	</div>
	
	



