package trade4fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trade4fun.pojo.filter.DOTA2Filter;
import trade4fun.service.DOTA2Service;
import trade4fun.utils.Result;

@Controller
@RequestMapping(value = "dota2")

public class DOTA2Controller {
	
	@Autowired
	private DOTA2Service dota2Service;
	
	
	@GetMapping(value = "")
	public String result(
			Model model,
			@RequestParam(value = "item_name", required = false) String item_name,
			@RequestParam(value = "rarity", required = false) String rarity,
			@RequestParam(value = "quality", required = false) String quality,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "hero", required = false) String hero,
			@RequestParam(value = "update_time", required = false, defaultValue="desc") String update_time,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page
			) {
		Result result = dota2Service.selectByName(item_name, rarity, quality, type, hero, update_time, price, min, max, page, 28);
		DOTA2Filter dota2Filter = new DOTA2Filter();
		dota2Filter.setItem_name(item_name);
		dota2Filter.setRarity(rarity);
		dota2Filter.setQuality(quality);
		dota2Filter.setType(type);
		dota2Filter.setHero(hero);
		dota2Filter.setUpdate_time(update_time);
		dota2Filter.setPrice(price);
		dota2Filter.setMin(min);
		dota2Filter.setMax(max);
		dota2Filter.setPage(page);
		model.addAttribute("filter", dota2Filter);
		model.addAttribute("result", result);
		return "dota2/dota2";
	}
	
	@GetMapping(value = "search/{page_size}")
	@ResponseBody
	public Result submit(
			@RequestParam(value = "item_name", required = false) String item_name,
			@RequestParam(value = "rarity", required = false) String rarity,
			@RequestParam(value = "quality", required = false) String quality,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "hero", required = false) String hero,
			@RequestParam(value = "create_time", required = false) String create_time,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@PathVariable("page_size") Integer page_size
			) {
		return dota2Service.selectByName(item_name, rarity, quality, type, hero, create_time, price, min, max, page, page_size);
	}
	
    //商品id对应的详细信息
    @GetMapping(value = "item/{item_id}")
    public String item(Model model, @PathVariable("item_id") Integer item_id) {
    	model.addAttribute("item", dota2Service.selectById(item_id));
    	return "dota2/dota2_item";
    }
	
}
