package trade4fun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import trade4fun.pojo.dto.GameDTO;

public interface RedisDao {
	
	List<GameDTO> select10csgo(@Param(value="price") Integer price);
	List<GameDTO> select10dota2(@Param(value="price") Integer price);
	
	List<Integer> select28id(String game_type);
	
	int update_csgo_updatetime(@Param(value="update_time") String update_time, @Param(value="ids") List<Integer> ids);
	int update_dota2_updatetime(@Param(value="update_time") String update_time, @Param(value="ids") List<Integer> ids);
	
}
