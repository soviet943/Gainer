package trade4fun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import trade4fun.dao.RedisDao;
import trade4fun.pojo.dto.GameDTO;
import trade4fun.service.Homepage_Service;
import trade4fun.utils.RedisUtil;
import trade4fun.utils.Result;


@Service
public class Homepage_ServiceImpl implements Homepage_Service {
	@Autowired
    private RedisUtil<GameDTO> redisUtil;
	@Autowired
	private RedisDao redisDao;
	
	//饰品推荐
	public Result<List<GameDTO>> random_csgo_10() {
		List<GameDTO> res = redisUtil.lall("random_csgo", GameDTO.class);
		if (res == null || res.size() == 0) {//如果第一次运行redis中没有，则从数据库中选取10个再放入redis中
			res = redisDao.select10csgo(null);
			redisUtil.rpushObject("random_csgo", GameDTO.class, res.toArray());
		}
		return Result.success(res);
	}
	public Result<List<GameDTO>> random_dota2_10() {
		List<GameDTO> res = redisUtil.lall("random_dota2", GameDTO.class);
		if (res == null || res.size() == 0) {//如果第一次运行redis中没有，则从数据库中选取10个再放入redis中
			res = redisDao.select10csgo(null);
			redisUtil.rpushObject("random_dota2", GameDTO.class, res.toArray());
		}
		return Result.success(res);
	}
	
	public Result<List<GameDTO>> random_csgo_rich_10(Integer price) {
		List<GameDTO> res = redisUtil.lall("random_csgo_rich", GameDTO.class);
		if (res == null || res.size() == 0) {//如果第一次运行redis中没有，则从数据库中选取10个再放入redis中
			res = redisDao.select10csgo(price);
			redisUtil.rpushObject("random_csgo_rich", GameDTO.class, res.toArray());
		}
		return Result.success(res);
	}
	public Result<List<GameDTO>> random_dota2_rich_10(Integer price) {
		List<GameDTO> res = redisUtil.lall("random_dota2_rich", GameDTO.class);
		if (res == null || res.size() == 0) {//如果第一次运行redis中没有，则从数据库中选取10个再放入redis中
			res = redisDao.select10csgo(price);
			redisUtil.rpushObject("random_dota2_rich", GameDTO.class, res.toArray());
		}
		return Result.success(res);
	}
	
}