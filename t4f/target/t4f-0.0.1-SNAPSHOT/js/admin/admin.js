//csgo查看饰品列表
$(
	function() {
		//左侧菜单栏选项滑动
		$(".Menu >a").click(function(){
			$(this).next().children().slideToggle();
			$(this).next().siblings(".toggle").children().slideUp();
		});
		//显示当前选项对应的界面，隐藏其他选项对应的界面
		$(".toggle a").click(function(){
			var i_d = $(this)[0].id;
			$(".content").css("display", "none");//隐藏所有一级选项全对应的窗口部
			$("#"+i_d+"_display").parent().css("display", "block");//显示选中的一级选项对应的窗口
			$("#"+i_d+"_display").siblings().css("display", "none");//隐藏二级选项对应的窗口全部
			$("#"+i_d+"_display").css("display", "block");//显示选中的二级选项对应的窗口
		})
		//选择要搜索的游戏类型
		$("#select_type").on("click", function(e){
			$("#js-search-drop").slideDown(200);
			$(document).one("click", function(){
				$("#js-search-drop").hide();
			});
			e.stopPropagation();
		})
		$(".form-search").children("input[name='search']").bind("keypress", function(event){
			if(event.keyCode == "13") {
				to_search();
			}
		})
		$("#js-search-drop").children("li").hover(function(){  
            $(this).css({"background":"#2C3040", "color":"#e4b35a"});  
        },function(){  
            $(this).css({"background":"#394552", "color":"#afb0b2"});  
        })  
		$("#js-search-drop").children("li").on("click", function(){
			$("#select_type").children("span").children("span:first").text($(this).children("span").text());
			$("#select_type").children("span").children("img").attr("src",$(this).children("img").attr("src"));
		})
		$(".toggle").children().bind("click", function(){
			$(".content").css({"background":"#2F3840", "opacity":"0.9"});
		})
	}
)
//游戏搜索
function to_search() {
	var selected_type = $("#select_type").children("span").children("span:first").text();
	if(selected_type == "CS:GO") {
		$(".content").css({"background":"#2F3840", "opacity":"0.9"});
		var tmp = $(".form-search").children("input[name='search']").val();
		load_csgo_search(tmp, 1);
	}
	if(selected_type == "DOTA2") {
		alert("dota2");
	}
	if(selected_type == "绝地求生") {
		alert("绝地求生");
	}
	if(selected_type == "H1Z1") {
		alert("H1Z1");
	}
}
//复用方法^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-
//清空输入框的内容
function cleartext() {
	var item_name = document.getElementById("item_name");
	var img = document.getElementById("csgoitemimg");
	var price = document.getElementById("price");
	$("input:radio").removeAttr("checked").parent().css("color", "#afb0b2");
	item_name.value = "";
	img.value = "";
	price.value = "";
}
//清空select选项
function removeselect(target) {//传入要清空的select的id名称
	$("#"+target).children().remove();
}
//取消select选项选中的状态
function clearselect(target) {
	var temp = $("#"+target).find("option:selected");
	temp.prop("checked", "");
}



//饰品查询结果分页 4参数< 1 2 3 4 5 6 7 >
function pagination(id, pages, current, target, a) {//参数:要分页的标签id，总页数，当前页数，分页连接地址的方法，搜索的关键词
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
    home.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + ", 1)'>首页</a>";
	ul.appendChild(home);
    //上一页
    var li_first = document.createElement("li");
    li_first.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + last + ")'>上一页</a>";
	ul.appendChild(li_first);
	//中间的数字
	if (pages <= 6) {
		for (i = 1; i <= pages; i++) {
			var li = document.createElement("li");
	        li.id = "li_" + i;
	        li.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + i + ")'>" + i + "</a>";
	        ul.appendChild(li);
		}
	}
	else {
		if(current <=4) {//最左面的情况
			for (i = 1; i <= 6; i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
		else if (current >= pages-2){//最右面的情况
			for (i = pages-3; i <= pages; i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
		else {//中间的情况
			for (i = current-3; i <= current+2; i++) {
				var li = document.createElement("li");
		        li.id = "li_" + i;
		        li.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + i + ")'>" + i + "</a>";
		        ul.appendChild(li);
			}
		}
	}
	//下一页
    var li_last = document.createElement("li");
    li_last.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + next + ")'>下一页</a>";
    ul.appendChild(li_last);
    //尾页
    var end = document.createElement("li");
    end.innerHTML = "<a onclick='" + target + "(" + '"'+a+'"' + "," + pages + ")'>尾页</a>";
	ul.appendChild(end);
    //选中的页面标签高亮
    document.getElementById("li_" + current).className = "active";
}


















