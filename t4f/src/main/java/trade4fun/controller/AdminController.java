package trade4fun.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trade4fun.pojo.CSGOItem;
import trade4fun.service.AdminService;
import trade4fun.service.CSGOService;
import trade4fun.utils.Result;
@Controller
@RequestMapping(value="admin")
	public class AdminController extends AbstractController {
	
		@Autowired
		private CSGOService csgoservice;
		@Autowired
		private AdminService adminservice;
		
//登录界面		
		@GetMapping(value = "home")
		public String index() {
			return "admin/home";
		}

//管理员登录操作
		@GetMapping(value = "login")
		public String login() {
			return "admin/login";
		}
		
		@PostMapping(value="login")
		@ResponseBody
		public Result login(String username, String password, HttpServletResponse response) {
			Result result = adminservice.login(username, password);
			if(result.isSuccess()) {
				Cookie cookie = new Cookie("token", (String) result.getData());
				cookie.setPath("/");
				response.addCookie(cookie);
				return Result.success();
			}
			return result;
		}
		
//用户操作
		@PostMapping(value = "getuser")
		@ResponseBody
		public Result getUser(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
			return adminservice.getUserByPage(page);
		}
		
		@PostMapping(value="getUserByName")
		@ResponseBody
		public Result getUserByName(String name) {
			return adminservice.getUserByName(name);
		}
		
		@PostMapping(value="getUserByEmail")
		@ResponseBody
		public Result getUserByEmail(String email) {
			return adminservice.getUserByEmail(email);
		}
		
		@PostMapping(value = "restrictuser")
	    @ResponseBody
	    public Result restrict(Integer uid) {
	        return adminservice.restrict_user(uid);
	    }

	    @PostMapping(value = "relieveuser")
	    @ResponseBody
	    public Result relieve(Integer uid) {
	        return adminservice.relieve_user(uid);
	    }

	    @PostMapping(value = "deluser")
	    @ResponseBody
	    public Result delUser(Integer uid) {
	        return adminservice.delete_user(uid);
	    }
		
//csgo操作
		@PostMapping(value = "addcsgoitem")
		@ResponseBody
		public Result addCSGOItem(CSGOItem item) {
			return adminservice.csgo_insert(item);
		}
		
		@PostMapping(value = "updatecsgoitem")
		@ResponseBody
		public Result updateGameInfo(CSGOItem item) {
			return adminservice.csgo_update(item);
		}
		
		@PostMapping(value = "deletecsgoitem")
		@ResponseBody
		public Result deleteCSGOItem(Integer id) {
			return adminservice.csgo_deleteByID(id);
		}
}


