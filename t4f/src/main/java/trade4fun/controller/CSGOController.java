package trade4fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trade4fun.pojo.filter.CSGOFilter;
import trade4fun.service.CSGOService;
import trade4fun.utils.Result;

@Controller
@RequestMapping(value = "csgo")
public class CSGOController {
	
	@Autowired
	private CSGOService csgoservice;
	
	@GetMapping(value = "getalltypes")
	@ResponseBody
	public Result getAllTypes(){
		return csgoservice.getAllTypes();
	}
	
	@GetMapping(value = "")
	public String submit1(
			Model model,
			@RequestParam(value = "item_name", required = false) String item_name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "item_type", required = false) String item_type,
			@RequestParam(value = "quality", required = false) String quality,
			@RequestParam(value = "rarity", required = false) String rarity,
			@RequestParam(value = "exterior", required = false) String exterior,
			@RequestParam(value = "update_time", required = false, defaultValue="desc") String update_time,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page
			) {
		Result result = csgoservice.selectByName(item_name, type, item_type, quality, rarity, exterior, update_time, price, min , max, page, 28);
		CSGOFilter csgoFilter = new CSGOFilter();
		csgoFilter.setItem_name(item_name);
		csgoFilter.setType(type);
		csgoFilter.setItem_type(item_type);
		csgoFilter.setQuality(quality);
		csgoFilter.setRarity(rarity);
		csgoFilter.setExterior(exterior);
		csgoFilter.setUpdate_time(update_time);
		csgoFilter.setPrice(price);
		csgoFilter.setMin(min);
		csgoFilter.setMax(max);
		csgoFilter.setPage(page);
		model.addAttribute("filter", csgoFilter);
		model.addAttribute("result", result);
		return "csgo/csgo";
	}
	
	@GetMapping(value = "search/{page_size}")
	@ResponseBody
	public Result submit2(
			@RequestParam(value = "item_name", required = false) String item_name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "item_type", required = false) String item_type,
			@RequestParam(value = "quality", required = false) String quality,
			@RequestParam(value = "rarity", required = false) String rarity,
			@RequestParam(value = "exterior", required = false) String exterior,
			@RequestParam(value = "update_time", required = false, defaultValue="desc") String update_time,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@PathVariable("page_size") Integer page_size
			) {
		return csgoservice.selectByName(item_name, type, item_type, quality, rarity, exterior, update_time, price, min , max, page, page_size);
	}
	
	
	@GetMapping(value = "csgo_hot")
	@ResponseBody
	public Result submit() {
		return csgoservice.csgo_hot();
	}
	
	//商品id对应的详细信息
    @GetMapping(value = "item/{item_id}")
    public String item(Model model, @PathVariable("item_id") Integer item_id) {
    	model.addAttribute("item", csgoservice.selectById(item_id));
    	return "csgo/csgo_item";
    }
	
}
