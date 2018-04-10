package trade4fun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trade4fun.dao.DOTA2Dao;
import trade4fun.pojo.DOTA2Item;
import trade4fun.service.DOTA2Service;
import trade4fun.utils.MsgCenter;
import trade4fun.utils.PageUtil;
import trade4fun.utils.Result;

@Service
public class DOTA2ServiceImpl implements DOTA2Service {

	@Autowired
	private DOTA2Dao dota2Dao;
	
	public Result<List<DOTA2Item>> selectByName(String name, String rarity, String quality, String type, String hero,
			String update_time, String price, Double min, Double max, Integer page, Integer pagesize) {
		PageUtil pageUtil = new PageUtil(dota2Dao.selectByName_N(name, rarity, quality, type, hero, min, max), page, pagesize);
		List<DOTA2Item> items = dota2Dao.selectByName(name, rarity, quality, type, hero, update_time, price, min, max, pageUtil.getStartPos(), pagesize);
		if(items == null) {
			return Result.fail(MsgCenter.NOT_FOUND);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", items);
		map.put("page", pageUtil);
		return Result.success(map);
	}

	public Result<DOTA2Item> selectById(Integer id) {
		DOTA2Item item = dota2Dao.selectById(id);
		if(item == null) {
			return Result.fail(MsgCenter.NOT_FOUND);
		}
		return Result.success(item);
	}

}
