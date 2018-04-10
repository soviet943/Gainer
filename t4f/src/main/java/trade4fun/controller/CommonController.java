package trade4fun.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import trade4fun.pojo.User;
import trade4fun.service.UserService;
import trade4fun.utils.MsgCenter;
import trade4fun.utils.Result;
import trade4fun.utils.UserHolder;

@Controller
public class CommonController extends AbstractController {

	@Autowired
	private UserHolder userHolder;
	@Autowired
	private UserService userService;
	@Autowired
    private Producer captchaProducer;

//跳转到登录页面
	@GetMapping(value = "login")
	public String login() {
		return "common/login";
	}
//跳转注册页面
	@GetMapping(value = "regist")
	public String register() {
		return "common/regist";
	}

//用户登录注册基本操作
//登录
	@PostMapping(value = "login")
    @ResponseBody
    public Result login(@RequestParam(value="username") String username, @RequestParam(value="password") String password,
    					@RequestParam(value = "remember", defaultValue = "false", required = false) boolean remember,
    					@RequestParam("captchacode") String captchacode,
    					HttpServletRequest request, HttpServletResponse response) {
        String referer = getReferer();
        if (userHolder.getUser() != null && userHolder.getUser().getStat().equals(User.STAT_OK)) {
            return Result.fail("您已登录");
        }
        //验证码部分
        String generateCode =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!generateCode.equals(captchacode)) {
        	return Result.fail("验证码不正确");
        }
        //用户信息验证
        Result result = userService.login(username, password, remember, this.getRemoteIp(), this.getUserAgent());
        if (result.isSuccess()) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("referer", referer);
            // response中添加cookie，以后每次请求都会带上cookie
            Cookie cookie = new Cookie("token", (String) result.getData());
            cookie.setPath("/");
            if (remember) {
                cookie.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cookie.setMaxAge(60 * 60 * 24);
            }
            response.addCookie(cookie);
            return Result.success(data);
        } else {
            return result;
        }
    }
//注册
    @PostMapping(value = "register")
    @ResponseBody
    public Result register(@RequestParam("regist_username") String username, @RequestParam("regist_password") String password,
			@RequestParam("regist_email") String email, @RequestParam("captchacode") String captchacode,
    		HttpServletRequest request, HttpServletResponse response) {
    	//验证码部分
        String generateCode =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!generateCode.equals(captchacode)) {
        	return Result.fail("验证码不正确");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        Result result = userService.register(user);
        if (result.isSuccess()) {
        	// 注册成功就自动登录，前台跳转到验证页面
            autologin(username, password, response);
            return Result.success();
        }
        return result;
    }
//取消登录  三步骤 1、得到浏览器中的cookie 2、调用userService方法删除redis中的cookie键值 3、紧接着更改数据库token
    @PostMapping(value = "logout")
    @ResponseBody
    public Result logout() {
        String token = null;
        // 从请求中获取token
        if (this.getRequest().getCookies() != null) {
            for (Cookie cookie : this.getRequest().getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        return userService.logout(token);
    }

    //生成验证码图片
    @RequestMapping("getCaptchaCode")
    public ModelAndView getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        String capText = captchaProducer.createText();  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }
        return null;
    }
  
    //注册完自动登录方法
    public Result autologin(String username, String password, HttpServletResponse response) {
		String referer = getReferer();
		if (userHolder.getUser() != null && userHolder.getUser().getStat().equals(User.STAT_OK)) {
			return Result.fail(MsgCenter.OK, referer);
		}
		//用户信息验证
		Result result = userService.login(username, password, false, this.getRemoteIp(), this.getUserAgent());
		if (result.isSuccess()) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("referer", referer);
			// response中添加cookie，以后每次请求都会带上cookie
			Cookie cookie = new Cookie("token", (String) result.getData());
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
			return Result.success(data);
			} else {
				return result;
			}
}
    
//得到跳转之前的链接，判定redirect向哪边	
    private String getReferer() {
        String referer = null;
        String tmp = this.getRequest().getHeader("Referer");
        if (tmp == null) {// 如果为空，不是从本站跳转过来的，应该跳转到首页
            referer = "/";
        } else if (tmp.endsWith("/login")) {//如果是从登录界面过来的，那么跳转到在登录界面之前的链接
            referer = (String) this.getSession().getAttribute("Referer");
        } else {
            referer = tmp;
        }
        this.getSession().setAttribute("Referer", referer);
        return referer;
    }

}
