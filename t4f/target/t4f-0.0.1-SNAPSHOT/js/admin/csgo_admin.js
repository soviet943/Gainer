/*
☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
=============================================CSGO部分=====================================================================
☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
*/

$(function(){
	//左上角显示指针所悬停的行对应的图片
	$("#iteminfo").delegate("tr","mouseover",function(){
		var a = $(this).attr("id");
	    var displayimg = csgoresult.data.csgoitem[a].img;
	    $("#logo").css("display", "none");
	    $("#csgodisplay").css("display", "block");
	    $("#csgodisplay").attr("src", "http://120.78.164.143:8080/images/csitem/"+displayimg+".png");
	})
	$("#iteminfo").delegate("tr","mouseout",function(){
	    $("#logo").css("display", "block");
	    $("#csgodisplay").css("display", "none");
	})
	
})

var csgotype;//csgo父类型
var csgoitemtype;//csgo子类型
var csgomap//csgo 父类和子类 map集合
var csgoresult;//存放查询结果
function initial_csgo() {
	$.ajax({
		type: "GET",
		url: "csgo/getalltypes",
		async: false,
		success: function(result) {
			csgotype = result.data.type;
			csgoitemtype = result.data.itemtype;
			csgomap = result.data.map;
		}
	});
}
//csgo搜索列表（更新、删除）
function load_csgo_search(search_name, pagenum) {
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
		url: "csgo/search/15",
		data: {item_name: search_name, page: pagenum},
		async: false,
		success: function (result) {
		csgoresult = result;
		for(i = 0; i < 15; i++) {
			var tr = document.createElement("tr");
			tr.id = i;
			var c = new Date(result.data.csgoitem[i].update_time);
			update_time = c.getFullYear() + ""+ (c.getMonth() + 1) +""+ c.getDate() +"";
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
				+ update_time + "</td><td>"
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
//csgo添加++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function load_csgo_add() {
	if(csgotype == undefined){
		initial_csgo();
	}
	$("#type_all").children().remove();
	$("#itemtype_all").children().remove();
	var type_all = document.getElementById("type_all");
		//将父选项罗列出来
	for(var key of csgotype) {
		var lb = document.createElement("label");
		lb.innerHTML = "<input type='radio' name='type' value="
			+ key + ">" + key;
		type_all.appendChild(lb);
	}
  //子选项按照已选的父选项罗列出来
  $("#type_all").change(function(){
	  $("#itemtype_all").children("label").remove();
	  var selectedtype = $("input:radio[name='type']:checked").val();
	  var itemtype_all = document.getElementById("itemtype_all");
	  for(var key of csgomap[selectedtype]) {
		  var lab = document.createElement("label");
		  var itemtypename = key
		  lab.innerHTML = "<input type='radio' name='itemtype' value= " + itemtypename + ">" + itemtypename;
		  itemtype_all.appendChild(lab);
	  }
	  $(this).children("label").css("color", "#afb0b2");
	  $(this).children("label").children("input:radio:checked").parent().css("color","#e4b35a");
  })
  //添加radio按钮选中颜色改变
  $("#itemtype_all").change(function(){
	  $(this).children("label").css("color", "#afb0b2");
	  $(this).children("label").children("input:radio:checked").parent().css("color","#e4b35a");
  })
  $("#exterior").change(function(){
	  $(this).children("label").css("color", "#afb0b2");
	  $(this).children("label").children("input:radio:checked").parent().css("color","#e4b35a");
  })
  $("#quality").change(function(){
	  $(this).children("label").css("color", "#afb0b2");
	  $(this).children("label").children("input:radio:checked").parent().css("color","#e4b35a");
  })
  $("#rarity").change(function(){
	  $(this).children("label").css("color", "#afb0b2");
	  $(this).children("label").children("input:radio:checked").parent().css("color","#e4b35a");
  })
}
//csgo上传添加内容
function addcsgoitem() {
	var form = new FormData();
	var item_name = document.getElementById("item_name").value;
	var type = $("input:radio[name='type']:checked").val();
	var item_type = $("input:radio[name='itemtype']:checked").val();
	var price = document.getElementById("price").value;
	var exterior = $("input:radio[name='exterior']:checked").val();
	var quality = $("input:radio[name='quality']:checked").val();
	var rarity = $("input:radio[name='rarity']:checked").val();
	var imgfakepath = document.getElementById("csgoitemimg").value;
	var img = imgfakepath.replace("C:\\fakepath\\","F:\\CSGO\\csitem\\");
	$.post("admin/addcsgoitem", {
		id: id,
		item_name: item_name,
		type: type,
		item_type: item_type,
		price: price,
		exterior: exterior,
		quality: quality,
		rarity: rarity,
		img: img
	}, function (result) {
		if(result.success) {
			$("#addmsg").html(result.msg).show(300).delay(2000).hide(300); 
			cleartext();
		}
		else {
			$("#addmsg").html(result.msg).show(300).delay(2000).hide(300); 
			cleartext();
		}
	})
}
//csgo更新操作的模态框XXXXXXXXXXXXXXXXXXXXXXXXX
function csgo_modify(id, item_name, type, item_type, price, exterior, quality, rarity, img) {
	var item_name_input = document.getElementById("update_name");
	var price_input = document.getElementById("update_price");
	item_name_input.value = item_name;
	price_input.value = price;
	$("#update_id").text(id);
	$("#update_exterior").children("#"+exterior).prop("selected", "selected");
	$("#update_quality").children("#"+quality).prop("selected", "selected");
	$("#update_rarity").children("#"+rarity).prop("selected", "selected");
	$("#update_itemtype").children().remove();
	//select部分
	//设置父选项，并设定默认的父选项
	for(var key of csgotype) {
		var update_type_option = document.createElement("option");
		if(key == type) {
			update_type_option.setAttribute("selected", "true");
		}
		update_type_option.setAttribute("value", key);
		update_type_option.innerHTML = key;
		$("#update_type_select").append(update_type_option);
	}
	// 设置子选项，并设定默认的子选项
	for(var key of csgomap[type]) {
		var update_itemtype_option = document.createElement("option");
		if(key == item_type) {
			update_itemtype_option.setAttribute("selected", "true");
		}
		update_itemtype_option.setAttribute("value", key);
		update_itemtype_option.innerHTML = key;
		$("#update_itemtype").append(update_itemtype_option);
	}
	// 若父选项改动，则更新子选项=======================================
	$("#update_type_select").change(function(){
		$("#update_itemtype").children().remove();
		var j = 0;
		var selectedtype = $("#update_type_select option:selected").val();//找到已选的父选项
		for(var key of csgomap[selectedtype]) {
			var update_itemtype_option = document.createElement("option");
			if(key == item_type) {
				update_itemtype_option.setAttribute("selected", "true");
			}
			update_itemtype_option.setAttribute("value", key);
			update_itemtype_option.innerHTML = key;
			$("#update_itemtype").append(update_itemtype_option);
		}
	});
}
	function updatecsgoitem() {
		var id = document.getElementById("update_id").innerHTML;
		var item_name = document.getElementById("update_name").value;
		var type = document.getElementById("update_type_select").value;
		var item_type = document.getElementById("update_itemtype").value;
		var price = document.getElementById("update_price").value;
		var exterior = document.getElementById("update_exterior").value;
		var quality = document.getElementById("update_quality").value;
		var rarity = document.getElementById("update_rarity").value;
		var img = document.getElementById("update_img").value;
		$.post("admin/updatecsgoitem", {
			id: id,
			item_name: item_name,
			type: type,
			item_type: item_type,
			price: price,
			exterior: exterior,
			quality: quality,
			rarity: rarity,
			img: img
		}, function (result) {
			if(result.success) {
				alert("修改成功");
				var search_item = $(".form-search").children("input[name='search']").val();
				removeselect('update_type_select');removeselect('update_itemtype');clearselect('update_quality');clearselect('update_rarity');clearselect('update_exterior');
				$("#csgo_modify_modal").modal('hide')
				show_csgo_select(search_item);
				var csgo_search = document.getElementById("csgo_search")
				csgo_search.value = "";
			}
			else {
				alert(result.msg);
			}
		})
	}
//删除操作	
function del(id_) {
	var msg = "您确定要删除吗？";
	if(confirm(msg)==true){
		$.post("admin/deletecsgoitem", {id: id_}, function(result) {
			if(result.success) {
				show_csgo_select();
				alert("删除成功");
			} else {
				alert("删除失败");
			}
		})
	} 
}
//csgo上传图片并预览
function setImagePreview() {
	//获取选择图片的对象
	var imgUpload = document.getElementById("csgoitemimg");
	//预览上传的图片的对象
	var imgPreview = document.getElementById("imgpreview");
	imgPreview.innerHTML = "";
	//得到图片文件
	var imgFile = imgUpload.files[0];
	imgPreview.innerHTML = "<div style='float:left' > <img id='picpreview' /> </div>";
	//获取预览图片对象
	var imgObjPreview = document.getElementById("picpreview");
    //火狐下，直接设img属性
	imgObjPreview.style.display = 'block';
	imgObjPreview.style.width = '220px';
	imgObjPreview.style.height = '150px';
    //imgObjPreview.src = docObj.files[0].getAsDataURL();
    //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要以下方式
    imgObjPreview.src = window.URL.createObjectURL(imgUpload.files[0]);//获取上传图片文件的物理路径
    return true;
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
function del(id_) {
	var msg = "您确定要删除吗？";
	if(confirm(msg)==true){
		$.post("admin/deletecsgoitem", {id: id_}, function(result) {
			if(result.success) {
				show_csgo_select();
				alert("删除成功");
			} else {
				alert(result.msg);
			}
		})
	} 
}