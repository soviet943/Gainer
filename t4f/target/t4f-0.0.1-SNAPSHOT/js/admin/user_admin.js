$(function(){
	
})

//用户搜索列表（更新、删除）
function load_user_search(search_name, pagenum) {
	if(csgotype == undefined){
		initial_csgo();
	}
	$("#iteminfo").children().remove();
	$("#paging_0").children().remove();
	$(".content").children().css("display", "none");
	$("#csgo_select_display").css("display", "block");
	var iteminfo = document.getElementById("iteminfo");
	$.ajax({
		type: 'GET',
		url: "admin/search_15",
		data: {item_name: search_name, page: pagenum},
		async: false,
		success: function (result) {
		csgoresult = result;
		for(i = 0; i < 15; i++) {
			var tr = document.createElement("tr");
			tr.id = i;
			var c = new Date(result.data.csgoitem[i].createTime);
			createtime = c.getFullYear() + ""+ (c.getMonth() + 1) +""+ c.getDate() +"";
			tr.innerHTML = "<td>"
				+ result.data.csgoitem[i].id + "</td><td>"
				+ result.data.csgoitem[i].item_name + "</td><td>"
				+ result.data.csgoitem[i].type + "</td><td>"
				+ result.data.csgoitem[i].item_type + "</td><td>"
				+ result.data.csgoitem[i].price + "</td><td>"
				+ result.data.csgoitem[i].exterior + "</td><td>"
				+ result.data.csgoitem[i].quality + "</td><td>"
				+ result.data.csgoitem[i].rarity + "</td><td>"
				+ result.data.csgoitem[i].sales + "</td><td>"
				+ createtime + "</td><td>"
				+"<button data-toggle='modal' " 
				+"onclick='csgo_modify(" +'"'+result.data.csgoitem[i].id+'"'+ ','
										 +'"'+result.data.csgoitem[i].item_name+'"'+ ','
										 +'"'+result.data.csgoitem[i].type+'"'+ ','
										 +'"'+result.data.csgoitem[i].item_type+'"'+ ','
										 +'"'+result.data.csgoitem[i].price+'"'+ ','
										 +'"'+result.data.csgoitem[i].exterior+'"'+ ','
										 +'"'+result.data.csgoitem[i].quality+'"'+ ','
										 +'"'+result.data.csgoitem[i].rarity+'"'+ ','
										 +'"'+result.data.csgoitem[i].img+'"'+
									  ")' data-target='#csgo_modify_modal' id='csgo_modify_btn'>修改</button>&nbsp;" +
									  "<button onclick='del("+result.data.csgoitem[i].id+")'>删除</button></td>";
			iteminfo.appendChild(tr);
		}
	}
	});
	var pages = csgoresult.data.page.pages;
	var current = csgoresult.data.page.current;
	pagination("paging_0", pages, current, "load_csgo_search", search_name);
}