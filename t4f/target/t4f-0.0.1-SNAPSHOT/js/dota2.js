$(function(){
		label();
		var pages = $("#pages").val();
		var current = $("#current").val();
		pagination("paging", pages, current, "submit_input");
//======================左侧标签筛选部分=============================
	    $(".filter-parameter").bind("click", function(){
	    	$(this).parent().children().removeClass("white-box");
	    	$(this).addClass("white-box");
	    	//根据稀有度筛选
	    	if($(this).parent().attr("id") == "filter-rarity"){
	    		$("#rarity").attr("value",$(this).text());
	    		if($("#rarity").attr("value") == "不限") {
					$("#rarity").attr("value","");
				}
				submit_input();
	    	}
	    	//根据品质筛选
	    	if($(this).parent().attr("id") == "filter-quality"){
	    		$("#quality").attr("value",$(this).text());
	    		if($("#quality").attr("value") == "不限") {
					$("#quality").attr("value","");
				}
				submit_input();
	    	}
	    	//根据类型筛选
	    	if($(this).parent().attr("id") == "filter-type"){
	    		$("#type").attr("value",$(this).text());
	    		if($("#type").attr("value") == "不限") {
					$("#type").attr("value","");
				}
				submit_input();
	    	}
	    });
	    //英雄筛选
	    $(".mask").bind("click", function(){
	    	var hero = $(this).attr("alt")
	    	$("#hero").attr("value",hero)
	    	submit_input();
	    })
	    $(".mask").hover(function(){
	    	$(this).addClass("mask_hover")
	    },function(){
	    	$(this).removeClass("mask_hover")
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
	    	if(attr_id == "f-rarity") {
	    		$("#rarity").attr("value","");
	    		submit_input();
	    	}
	    	if(attr_id == "f-type") {
	    		$("#type").attr("value","");
	    		submit_input();
	    	}
	    	if(attr_id == "f-hero") {
	    		$("#hero").attr("value","");
	    		submit_input();
	    	}
	    });
	    
	  //搜索框
	    $("#search-submit").bind("click",function(){
	    	submit_input();
	    })
	    $("#item_name").bind("keypress",function(event){
	    	if(event.keyCode == "13") {
	    		submit_input();
	    	}
	    })
	})

//=============================================================================
//标签初始化显示方法
function label(){
	var quality = $("#quality").val();
	var rarity = $("#rarity").val();
	var type = $("#type").val();
	var hero = $("#hero").val();
	var update_time = $("#update_time").val();
	var price = $("#price").val();
	//选中的标签加上白色边框，并将已选的标签在filter-display中显示出来
	if(quality != "") {
		$(".filter-parameter:contains("+quality+")").addClass("white-box");
		$(".filter-display").append("<a id='f-quality' class='sum-filter'>" + quality +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(rarity != "") {
		$(".filter-parameter:contains("+rarity+")").addClass("white-box");
		$(".filter-display").append("<a id='f-rarity' class='sum-filter'>" + rarity +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(type != "") {
		$(".filter-parameter:contains("+type+")").addClass("white-box");
		$(".filter-display").append("<a id='f-type' class='sum-filter'>" + type +"&nbsp;&nbsp;&nbsp;</a>")
	}
	if(hero != "") {
		$(".mask[alt="+hero+"]").addClass("mask_choose");
		$(".filter-display").append("<a id='f-hero' class='sum-filter'>" + hero +"&nbsp;&nbsp;&nbsp;</a>")
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

//提交查询条件
function submit_input(i) {
	var list = {
		"item_name" : $("#item_name").val(),
		"rarity" : $("#rarity").val(),
		"quality" : $("#quality").val(),
		"type" : $("#type").val(),
		"hero" : $("#hero").val(),
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


//饰品查询结果分页 < 1 2 3 4 5 6 >
//参数: 0、分页的标签id 1、总页数  2、当前页数  3、方法名称  4、参数
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

