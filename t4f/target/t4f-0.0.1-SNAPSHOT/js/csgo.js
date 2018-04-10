var urlparam=[];
$(function(){
		csgo_hot();
		label();
		//csgo-nav-bar枪械型号展示
		$(".navbar-csgo li").each(function toggle(){
		    $(this).hover(function(){
		         var slideChild = $(this).children(".nav_list_csgo");
		         slideChild.stop(true, false).slideToggle(243,"swing");
		    });
		});
	    $(".nav-data li a").mouseover(function() {
	        var pic = $(this).attr('img_url');
	        $(this).parent().parent().parent(".nav_list_csgo").children("div").children("img").attr('src', pic);
	    });
	    var pages = $("#pages").val();
		var current = $("#current").val();
		pagination("paging", pages, current, "submit_input");
//======================左侧标签筛选部分=============================
	    //外观品质稀有度点击触发事件	    
	    $(".filter-parameter").bind("click", function(){
	    	if($(this).parent().attr("id") == "filter-exterior"){
	    		$("#exterior").attr("value",$(this).text());
	    		if($("#exterior").attr("value") == "不限") {
					$("#exterior").attr("value","");
				}
				submit_input();
	    	}
	    	if($(this).parent().attr("id") == "filter-quality"){
	    		$("#quality").attr("value",$(this).text());
	    		if($("#quality").attr("value") == "不限") {
					$("#quality").attr("value","");
				}
				submit_input();
	    	}
	    	if($(this).parent().attr("id") == "filter-rarity"){
	    		$("#rarity").attr("value",$(this).text());
	    		if($("#rarity").attr("value") == "不限") {
					$("#rarity").attr("value","");
				}
				submit_input();
	    	}
	    })
	    //根据枪械种类查询
	    $(".navbar-csgo").children("li").bind("click", function(){
	    	$("#item_type").attr("value","");
	    	$("#type").attr("value",$(this).children("#type_name").text());
	    	submit_input();
	    })
	    //根据枪械的具体型号查询
	    $(".nav-data").children("li").children("a").bind("click", function(e){
	    	e.stopPropagation();
	    	$("#type").attr("value","");
	    	$("#item_type").attr("value",$(this).text());
	    	submit_input();
	    })
	    
	    //根据时间升序/降序
	    $("#orderByTime").click(function(){
	    	$("#price").attr("value","");
			if($("#time-asc").css("display")=="none" && $("#time-desc").css("display")=="none"){
				$("#update_time").attr("value","asc");
				submit_input();
			}
			else if($("#time-desc").css("display")=="none" && $("#time-asc").css("display")=="inline"){
				$("#update_time").attr("value","desc");
				submit_input();
			}
			else {
				$("#update_time").attr("value","asc");
				submit_input();
			}
	    })
	    //根据价格升序/降序
	    $("#orderByPrice").click(function(){
	    	$("#update_time").attr("value","null");
			if($("#price-asc").css("display")=="none" && $("#price-desc").css("display")=="none"){
				$("#price").attr("value","asc");
				submit_input();
			}
			else if($("#price-desc").css("display")=="none" && $("#price-asc").css("display")=="inline"){
				$("#price").attr("value","desc");
				submit_input();
			}
			else {
				$("#price").attr("value","asc");
				submit_input();
			}
	    });
	    
	  //点击取消已筛选的标签
	    $(".filter-display").delegate(".sum-filter","click", function(){
	    	var attr_id = $(this).attr("id");
	    	if(attr_id == "f-quality") {
	    		$("#quality").attr("value","");
	    		submit_input();
	    	}
	    	if(attr_id == "f-exterior") {
	    		$("#exterior").attr("value","");
	    		submit_input();
	    	}
	    	if(attr_id == "f-rarity") {
	    		$("#rarity").attr("value","");
	    		submit_input();
	    	}
	    	if(attr_id == "f-type") {
	    		$("#type").attr("value","");
	    		submit_input();
	    	}
	    	if(attr_id == "f-item_type") {
	    		$("#item_type").attr("value","");
	    		submit_input();
	    	}
	    });
	    
	  //搜索框
	    $("#search-submit").bind("click",function(){
	    	$("#quality").attr("value","");
	    	$("#exterior").attr("value","");
	    	$("#rarity").attr("value","");
	    	$("#type").attr("value","");
	    	$("#item_type").attr("value","");
	    	submit_input();
	    })
	    $("#item_name").bind("keypress",function(event){
	    	if(event.keyCode == "13") {
	    		$("#quality").attr("value","");
		    	$("#exterior").attr("value","");
		    	$("#rarity").attr("value","");
		    	$("#type").attr("value","");
		    	$("#item_type").attr("value","");
	    		submit_input();
	    	}
	    })
    
})
//=============================================================================
//标签初始化显示方法
function label(){
	var exterior = $("#exterior").val();
	var quality = $("#quality").val();
	var rarity = $("#rarity").val();
	var type = $("#type").val();
	var item_type = $("#item_type").val();
	var update_time = $("#update_time").val();
	var price = $("#price").val();
	//选中的标签加上白色边框，并将已选的标签在filter-display中显示出来
	if(exterior != "") {
		$(".filter-parameter:contains("+exterior+")").addClass("white-box");
		$(".filter-display").append("<a id='f-exterior' class='sum-filter'>" + exterior +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(quality != "") {
		$(".filter-parameter:contains("+quality+")").addClass("white-box");
		$(".filter-display").append("<a id='f-quality' class='sum-filter'>" + quality +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(rarity != "") {
		$(".filter-parameter:contains("+rarity+")").addClass("white-box");
		$(".filter-display").append("<a id='f-rarity' class='sum-filter'>" + rarity +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(type != "") {
		$(".filter-display").append("<a id='f-type' class='sum-filter'>" + type +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(item_type != "") {
		$(".filter-display").append("<a id='f-item_type' class='sum-filter'>" + item_type +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(price == "asc") {
		$("#orderByPrice").addClass("active");
		$("#orderByPrice").children("a").css("color", "#e4b35a");
		$("#price-asc").css("display", "inline");
	}
	if(price == "desc") {
		$("#orderByPrice").addClass("active");
		$("#orderByPrice").children("a").css("color", "#e4b35a");
		$("#price-desc").css("display", "inline");
	}
	if(update_time == "asc") {
		$("#orderByTime").addClass("active");
		$("#orderByTime").children("a").css("color", "#e4b35a");
		$("#time-asc").css("display", "inline");
	}
	if(update_time == "desc") {
		$("#orderByTime").addClass("active");
		$("#orderByTime").children("a").css("color", "#e4b35a");
		$("#time-desc").css("display", "inline");
	}
	
}
	
function submit_input(i) {
	var list = {
		"item_name" : $("#item_name").val(),
		"type" : $("#type").val(),
		"item_type" : $("#item_type").val(),
		"exterior" : $("#exterior").val(),
		"quality" : $("#quality").val(),
		"rarity" : $("#rarity").val(),
		"update_time" : $("#update_time").val(),
		"price" : $("#price").val(),
		"min" : $("#min").val(),
		"max" : $("#max").val(),
		"page" : i || 1
	}
	str = "";
	var first = true;
	for(var key in list) {
		if(list[key] == "") {continue}
		if(first) {
			str = str + "?" + key + "=" + list[key];
			first = false;
		} else {
			str = str + "&" + key + "=" + list[key];
		}
	}
	var url = window.location.pathname + str;
	window.location.href = url;
}	


//热卖排行
function csgo_hot() {
	$(".side-list").children().remove();
	$.get("csgo/csgo_hot", function(result){
		$.each(result.data, function(i, each_item){
			var inner_content = 
			'<li>'+
				'<div class="csgo-img-bg-hot">'+
					'<img src="http://120.78.164.143:80/images/csitem/' + each_item.img + '.png">'+
					'<span class="rarity-box-hot">' + each_item.rarity +' </span>'+
				'</div>'+
				'<div class="info-hot">'+
					'<p class="name-hot">' + each_item.item_name + '</p>'+
					'<p>'+
						'已成交 ：'+
						'<span class="sold-hot">' + each_item.sales +' </span>'+
					'</p>'+
				'</div>'+
			'</li>'
			$(inner_content).appendTo(".side-list");
		});
	});
}


//饰品查询结果分页 < 1 2 3 4 5 6 >
//参数: 1、分页的标签id 2、总页数  3、当前页数  4、方法名称 
function pagination(id, pages, current, target) {//参数:要分页的标签id，总页数，当前页数，分页连接地址的方法，搜索的关键词
	$("#"+id).children().remove();
	var ul = document.getElementById(id);
    var last = current - 1;
    var next = current + 1;
    if (last < 1) {
        last = 1
    }
    if (next > pages) {
        next = pages
    }
    //首页
    var home = document.createElement("li");
    home.innerHTML = "<a onclick='" + target + "(1)'>首页</a>";
	ul.appendChild(home);
    //上一页
    var li_first = document.createElement("li");
    li_first.innerHTML = "<a onclick='" + target + "(" + last + ")'>上一页</a>";
	ul.appendChild(li_first);
	//中间的数字
	if (pages <= 6) {
		for (i = 1; i <= pages; i++) {
			var li = document.createElement("li");
	        li.id = "li_" + i;
	        li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
	        ul.appendChild(li);
		}
	}
	else {
		if(current <=4) {//最左面的情况
			for (i = 1; i <= 6; i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
		else if (current >= pages-2){//最右面的情况
			for (i = pages-3; i <= pages; i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
		else {//中间的情况
			for (i = parseInt(current) - parseInt(3); i <= parseInt(current) + parseInt(2); i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
	}
	//下一页
    var li_last = document.createElement("li");
    li_last.innerHTML = "<a onclick='" + target + "(" + next + ")'>下一页</a>";
    ul.appendChild(li_last);
    //尾页
    var end = document.createElement("li");
    end.innerHTML = "<a onclick='" + target + "(" + pages + ")'>尾页</a>";
	ul.appendChild(end);
    //选中的页面标签高亮
    $("#li_" + current).addClass("active");
}
