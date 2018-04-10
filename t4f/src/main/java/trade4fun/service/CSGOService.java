package trade4fun.service;

import java.util.List;

import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.CSGOItemType;
import trade4fun.utils.Result;

public interface CSGOService {
	//获取所有种类
	Result<List<CSGOItemType>> getAllTypes();
	
	//通过分页查询所有饰品
	Result<List<CSGOItem>> selectByName(String name, String type, String item_type, String quality, String rarity, String exterior, String create_time, String price, Double min, Double max, Integer page, Integer pagesize);
	
	//查询热卖排行
	Result<List<CSGOItem>> csgo_hot();
	
	Result<CSGOItem> selectById(Integer id);
	
}

