package trade4fun.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trade4fun.dao.CSGODao;
import trade4fun.dao.CSGOTypeDao;
import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.CSGOItemType;
import trade4fun.service.CSGOService;
import trade4fun.utils.MsgCenter;
import trade4fun.utils.PageUtil;
import trade4fun.utils.Result;

@Service
public class CSGOServiceImpl implements CSGOService {
	
	@Autowired
	private CSGODao csgodao;
	@Autowired
	private CSGOTypeDao csgotypedao;
	
	public Result<List<CSGOItemType>> getAllTypes() {
		List<CSGOItemType> types = csgotypedao.getAllTypes();
		//所有父类型
		Set<String> type = new HashSet<String>();
		//所有子类型
		Set<String> itemtype = new HashSet<String>();
		//父类型-子类型 组成的键值对
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		if (types == null) {
			return Result.fail(MsgCenter.NOT_FOUND);
		}
		for(int i=0; i<types.size(); i++) {
			String typename = types.get(i).getCsgo_type_name();
			String subtypename = types.get(i).getCsgo_subtype_name();
			type.add(typename);
			itemtype.add(subtypename);
			if(map.containsKey(typename)) {
				map.get(typename).add(subtypename);
			}
			else {
				List<String> tmp = new ArrayList<String>();
				tmp.add(subtypename);
				map.put(typename, tmp);
			}
		}
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap.put("type", type);
		resultmap.put("itemtype", itemtype);
		resultmap.put("map", map);
		return Result.success(resultmap);
	}
	
	
	//模糊查询
	public Result<List<CSGOItem>> selectByName(String name, String type, String item_type, String quality, String rarity, String exterior, String update_time, String price, Double min, Double max, Integer page, Integer pagesize) {
		PageUtil pageUtil = new PageUtil(csgodao.selectByName_N(name, type, item_type, quality, rarity, exterior, min, max), page, pagesize);
		List<CSGOItem> items = csgodao.selectByName(name, type, item_type, quality, rarity, exterior, update_time, price, min, max, pageUtil.getStartPos(), pagesize);
		if(items == null) {
			return Result.fail(MsgCenter.NOT_FOUND);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("csgoitem", items);
		map.put("page", pageUtil);
		return Result.success(map);
	}
	
	//热卖排行
	public Result<List<CSGOItem>> csgo_hot() {
		List<CSGOItem> pop_items = csgodao.csgo_hot();
		if(pop_items == null) {
			return Result.fail(MsgCenter.NOT_FOUND);
		}
		return Result.success(pop_items);
	}


	public Result<CSGOItem> selectById(Integer id) {
		CSGOItem item = csgodao.selectById(id);
		if(item == null) {
			return Result.fail(MsgCenter.NOT_FOUND);
		}
		return Result.success(item);
	}
	

}
