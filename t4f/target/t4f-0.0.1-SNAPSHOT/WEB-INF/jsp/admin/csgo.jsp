<%@page pageEncoding="UTF-8"%>
<div class="content">
<!-- 显示列表 -->
<div class="m-right" id="csgo_select_display">
	<div class="col-md-12 column">
		<table class="table">
			<thead>
				<tr>
					<th>饰品id</th>
					<th>饰品名称</th>
					<th>枪械种类</th>
					<th>枪械型号</th>
					<th>价格</th>
					<th>外观</th>
					<th>品质</th>
					<th>稀有度</th>
					<th>销量</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="iteminfo">
			
			</tbody>
		</table>
	</div>
	<!-- 饰品显示分页 -->
	<div class="yema divsetcenter">
		<ul id="paging_0" class="pagination">

		</ul>
	</div>
</div>

<!-- 显示添加 -->
<div class="m-right" id="csgo_add_display">
	<div class="col-md-12 column">
	<br>
        <table class="table">
			<tr>
				<td>名称:&nbsp;&nbsp; <input type="text" name="item_name"
					placeholder="饰品名称" id="item_name">
				</td>
			</tr>
			<tr>
				<td id="type_all">枪械种类:&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td id="itemtype_all">枪械型号:&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>价格:&nbsp;&nbsp; <input type="text" name="price"
					placeholder="饰品价格" id="price">
				</td>
			</tr>
			<tr>
				<td id="exterior">外观:&nbsp;&nbsp; <label><input
						type="radio" name="exterior" value="无">无</label> <label><input
						type="radio" name="exterior" value="崭新出厂">崭新出厂</label> <label><input
						type="radio" name="exterior" value="略有磨损">略有磨损</label> <label><input
						type="radio" name="exterior" value="久经沙场">久经沙场</label> <label><input
						type="radio" name="exterior" value="破损不堪">破损不堪</label> <label><input
						type="radio" name="exterior" value="战痕累累">战痕累累</label> <label><input
						type="radio" name="exterior" value="无涂装">无涂装</label>
				</td>
			</tr>
			<tr>
				<td id="quality">品质:&nbsp;&nbsp; <label><input
						type="radio" name="quality" value="无">无</label> <label><input
						type="radio" name="quality" value="普通">普通</label> <label><input
						type="radio" name="quality" value="StatTrak™">StatTrak™</label> <label><input
						type="radio" name="quality" value="纪念品">纪念品</label> <label><input
						type="radio" name="quality" value="★">★</label>
				</td>
			</tr>
			<tr>
				<td id="rarity">稀有度:&nbsp;&nbsp; <label><input
						type="radio" name="rarity" value="无">无</label> <label><input
						type="radio" name="rarity" value="普通级">普通级</label> <label><input
						type="radio" name="rarity" value="军舰级">军舰级</label> <label><input
						type="radio" name="rarity" value="工业级">工业级</label> <label><input
						type="radio" name="rarity" value="受限">受限</label> <label><input
						type="radio" name="rarity" value="保密">保密</label> <label><input
						type="radio" name="rarity" value="隐秘">隐秘</label> <label><input
						type="radio" name="rarity" value="违禁">违禁</label>
				</td>
			</tr>
			<tr>
				<td>上传饰品图片:&nbsp;&nbsp; <input type="file" id="csgoitemimg"
					accept="image/*" onchange="setImagePreview();" name="csgoitemimg">
				</td>
			</tr>
			<tr>
				<td>
					<div id="imgpreview"></div>
				</td>
			</tr>
		</table>
		<button class="btn" id="addgamebtn" type="submit" onclick="addcsgoitem()"><span class="glyphicon glyphicon-ok"></span>确认添加</button>&nbsp;&nbsp;
		<button class="btn" type="reset" onclick="cleartext()"><span class="glyphicon glyphicon-remove"></span>重置内容</button>
		<span id="addmsg"></span>
	</div>
</div>
</div>

<!-- csgo模态框，修改操作 -->
<div class="modal" id="csgo_modify_modal" data-backdrop="static">
	<div class="modal-dialog">
		<div class="model-content">
			<div class="modal-body" id="csgo_modify" style="padding:0px;">
				&nbsp;&nbsp;ID:<label id="update_id" style="margin-left: 2px;"></label>
				<table>
					<tr>
	                 	<td>
	                 		枪械名称:&nbsp;&nbsp;
	                 		<input name="item_name" id="update_name" value="" style="width:300px;">
	                 	</td>
						<td>
							枪械种类:&nbsp;&nbsp;
							<select id="update_type_select" name="type">
							</select>
						</td>
                   		<td>
                   			枪械型号:&nbsp;&nbsp;
                   			<select id="update_itemtype" name="item_type">
							</select>
						</td>
                   		<td>
                   			价格:&nbsp;&nbsp;
                   			<input name="price" id="update_price" value="" style="width:100px;">
						</td>
						<td>
                   			外观:&nbsp;&nbsp;
                   			<select name="exterior" id="update_exterior">
                     			<option id="无" value="无">无</option>
                     			<option id="崭新出厂" value="崭新出厂">崭新出厂</option>
                     			<option id="略有磨损" value="略有磨损">略有磨损</option>
                     			<option id="久经沙场" value="久经沙场">久经沙场</option>
                     			<option id="破损不堪" value="破损不堪">破损不堪</option>
                     			<option id="战痕累累" value="战痕累累">战痕累累</option>
                     			<option id="无涂装" value="无涂装">无涂装</option>
                     		</select>
                     	</td>
                   		<td>
                   			品质:&nbsp;&nbsp;
                   			<select name="quality" id="update_quality">
                     			<option id="无" value="无">无</option>
                     			<option id="普通" value="普通">普通</option>
                     			<option id="StatTrak™" value="StatTrak™">StatTrak™</option>
                     			<option id="纪念品" value="纪念品">纪念品</option>
                     			<option id="★" value="★">★</option>
                     		</select>
                     	</td>
	                 	<td>
                   			稀有度:&nbsp;&nbsp;
                   			<select name="rarity" id="update_rarity">
                     			<option id="无" value="无">无</option>
                     			<option id="普通级" value="普通级">普通级</option>
                     			<option id="军舰级" value="军舰级">军舰级</option>
                     			<option id="工业级" value="工业级">工业级</option>
                     			<option id="受限" value="受限">受限</option>
                     			<option id="保密" value="保密">保密</option>
                     			<option id="隐秘" value="隐秘">隐秘</option>
                     			<option id="违禁" value="违禁">违禁</option>
                     		</select>
                     	</td>
              		  		<td>上传饰品图片:
                     		<input type="file" id="update_img" accept="image/*" onchange="setImagePreview();" name="csgoitemimg" style="width:100px;">
                     	</td>
					</tr>	               		  	
				</table>
				<div id="imgpreview"></div>
			</div>
			<div class="modal-footer" style="height:40px;padding:0px;">
				<button type="button" class="btn" data-dismiss="modal" 
					onclick="removeselect('update_type_select');removeselect('update_itemtype');clearselect('update_quality');clearselect('update_rarity');clearselect('update_exterior');">关闭</button>
				<button type="button" class="btn" onclick="updatecsgoitem()">提交更改</button>
			</div>
		</div>
	</div>
</div>
<script src="js/admin/csgo_admin.js"></script>