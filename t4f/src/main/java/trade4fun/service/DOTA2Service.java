package trade4fun.service;

import java.util.List;

import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.DOTA2Item;
import trade4fun.utils.Result;

public interface DOTA2Service {
	//根据筛选项分页查询
	Result<List<DOTA2Item>> selectByName(String name, String rarity, String quality, String type, String hero, String update_time, String price, Double min, Double max, Integer page, Integer pagesize);
	
	Result<DOTA2Item> selectById(Integer id);
}
