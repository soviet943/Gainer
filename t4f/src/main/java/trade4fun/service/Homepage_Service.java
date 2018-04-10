package trade4fun.service;

import java.util.List;

import trade4fun.pojo.dto.GameDTO;
import trade4fun.utils.Result;

public interface Homepage_Service  {
	
	public Result<List<GameDTO>> random_csgo_10();
	public Result<List<GameDTO>> random_dota2_10();
	
	public Result<List<GameDTO>> random_csgo_rich_10(Integer price);
	public Result<List<GameDTO>> random_dota2_rich_10(Integer price);
}
