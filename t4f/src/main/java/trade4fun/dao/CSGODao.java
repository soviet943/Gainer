package trade4fun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import trade4fun.pojo.CSGOItem;

@Repository
public interface CSGODao {
	
	CSGOItem selectById(Integer id);
	
	//多条件查询
	List<CSGOItem> selectByName(
			@Param(value = "item_name") String item_name, 
			@Param(value = "type") String type,
			@Param(value = "item_type") String item_type,
			@Param(value = "quality") String quality,
			@Param(value = "rarity") String rarity,
			@Param(value = "exterior") String exterior,
			@Param(value = "update_time") String update_time,
			@Param(value = "price") String price,
			@Param(value = "min") Double min,
			@Param(value = "max") Double max,
			@Param(value = "startPos") Integer startPos, 
			@Param(value = "size") Integer size
			);
	
	Integer selectByName_N(
			@Param(value = "item_name") String item_name, 
			@Param(value = "type") String type,
			@Param(value = "item_type") String item_type,
			@Param(value = "quality") String quality,
			@Param(value = "rarity") String rarity,
			@Param(value = "exterior") String exterior,
			@Param(value = "min") Double min,
			@Param(value = "max") Double max
			);
	
	List<CSGOItem> csgo_hot();
	
	
	int deleteByID(Integer id);
	int insert(CSGOItem pojo);
	int updateByID(CSGOItem pojo);
}
