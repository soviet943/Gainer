package trade4fun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import trade4fun.pojo.DOTA2Item;

@Repository
public interface DOTA2Dao {
	
	DOTA2Item selectById(Integer id);
	
	//多条件查询
	List<DOTA2Item> selectByName(
			@Param(value = "item_name") String item_name, 
			@Param(value = "rarity") String rarity,
			@Param(value = "quality") String quality,
			@Param(value = "type") String type,
			@Param(value = "hero") String hero,
			@Param(value = "update_time") String update_time,
			@Param(value = "price") String price,
			@Param(value = "min") Double min,
			@Param(value = "max") Double max,
			@Param(value = "startPos") Integer startPos, 
			@Param(value = "size") Integer size
			);
	
	Integer selectByName_N(
			@Param(value = "item_name") String item_name, 
			@Param(value = "rarity") String rarity,
			@Param(value = "quality") String quality,
			@Param(value = "type") String type,
			@Param(value = "hero") String hero,
			@Param(value = "min") Double min,
			@Param(value = "max") Double max
			);
	
	
	int deleteByID(Integer id);
	int insert(DOTA2Item pojo);
	int updateByID(DOTA2Item pojo);
}
