package trade4fun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import trade4fun.pojo.User;
import trade4fun.service.UserService;
import trade4fun.utils.MsgCenter;
import trade4fun.utils.Result;
import trade4fun.utils.UserHolder;

@CrossOrigin
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserHolder userHolder;
	
	@GetMapping(value = "test_validate")
	public String to() {
		return "help/validate";
	}
	
//激活用户三步骤
	//1、跳转到邮箱验证界面
	@GetMapping(value = "to_validate")
	public String validate(Model model) {
		String referer = getReferer();
		if(userHolder.getUser().getStat().equals(User.STAT_OK)) {
			return "redirect:" + referer;
		}
		model.addAttribute("mail", userHolder.getUser().getEmail());
		model.addAttribute("username", userHolder.getUser().getUsername());
		return "help/validate";
	}
	
	//2、点击发送邮件
	@PostMapping(value = "sendMail")
	@ResponseBody
	public Result sendMail() {
		if(userHolder.getUser() == null) {
			return Result.fail(MsgCenter.USER_NOT_LOGIN);
		}
		return userService.sendMail(userHolder.getUser());
	}
	
	//3、用户邮箱中点击链接激活
	@GetMapping(value = "validate/{uid}/{code}")
	public String validate(@PathVariable("uid") Integer uid, @PathVariable("code") String code) {
		Result result = userService.validate(uid, code);
		if(result.isSuccess()) {
			String referer = getReferer();
			return "redirect:" + referer;
		}
		return "help/validate";
	}
	
	
	@GetMapping(value = "personal")
	public String personal() {
		return "personal";
	}
	
	@PostMapping(value = "personal")
	@ResponseBody
	public Result getPersonInfo() {
		User user = userHolder.getUser();
		if(user == null) {
			return Result.fail(MsgCenter.USER_NOT_LOGIN);
		}
		user.setPassword(null);
		return Result.success(user);
	}
	
	
	@GetMapping(value = "update")
	public String update() {
		return "updateUserInfo";
	}
	
	@PostMapping(value = "update")
	@ResponseBody
	public Result update(User user) {
		return userService.updateUserInfo(user);
	}
	
	@GetMapping(value = "updatepassword")
	public String updatePassword() {
		return "updatepassword";
	}
	
	@PostMapping(value = "updatepassword")
	@ResponseBody
	public Result updatePassword(String password) {
		return userService.updatePassword(password);
	}
	
	@GetMapping(value = "findpassword")
	public String findPassword() {
		return "findpassword";
	}
	
	@PostMapping(value = "sendfetchpwdmail")
	@ResponseBody
	public Result sendFetchPwdMail(String email) {
		return userService.sendFetchPwdMail(email);
	}
	
	@PostMapping(value = "findpassword")
	@ResponseBody
	public Result findPassword(String password, String email, String code) {
		return userService.forgetPassword(password, email, code);
	}
	
	
	/**
     * 检查用户是否登陆，如果登陆就返回应该跳转到的页面，否则执行接下来的逻辑
     * 每次登陆之前都从request的header中获取跳转之前的链接referer
     * 如果为空（从别的网站跳转过来的），那么应该跳转到首页
     * 登陆流程中第一次登陆就会调用本方法获取跳转链接，登陆失败将referer写入session中
     * 如果是从登录页跳转过来的，可能是登陆出错了，但是跳转到登录页之前的referer在session存着，从session中获取
     * 如果sesson中有referer，那么登陆成功跳转到referer，并且从session中删除referer
     */
    private String getReferer() {
        String referer = null;
        String tmp = this.getRequest().getHeader("Referer");
        if (tmp == null) { // 如果为空，不是从本站跳转过来的，应该跳转到首页
            referer = "/t4f";
        } else if (tmp.endsWith("/login")) { //如果
            referer = (String) this.getSession().getAttribute("Referer");
        } else {
            referer = tmp;
        }
        this.getSession().setAttribute("Referer", referer);
        return referer;
    }
}
