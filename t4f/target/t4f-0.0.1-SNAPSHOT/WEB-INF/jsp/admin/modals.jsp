<%@page pageEncoding="UTF-8"%>
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
