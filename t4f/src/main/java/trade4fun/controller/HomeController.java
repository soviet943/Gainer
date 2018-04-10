package trade4fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import trade4fun.service.Homepage_Service;

@Controller
public class HomeController {
	
	@Autowired
	Homepage_Service homepage_service;
	
	//跳转到首页
	@RequestMapping(value = {"/", "/index"})
	public String index(Model model) {
		model.addAttribute("random_csgo", homepage_service.random_csgo_10());
		model.addAttribute("random_dota2", homepage_service.random_dota2_10());
		model.addAttribute("random_csgo_rich", homepage_service.random_csgo_rich_10(2000));
		model.addAttribute("random_dota2_rich", homepage_service.random_dota2_rich_10(2000));
		return "home/index";
	}
	
	
}
