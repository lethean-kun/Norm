package cn.com.zhiding.user.controller;



import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.zhiding.user.entity.User;
import cn.com.zhiding.user.service.ILoginService;
import cn.com.zhiding.util.RegexUtil;
import cn.com.zhiding.util.Result;


@Controller
@RequestMapping("/userLogin")
public class LoginController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ILoginService loginService;
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Result login(HttpServletRequest request,HttpServletResponse response){
		log.info("进入登录页面");
		Result result = new Result();
		User user = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		if(RegexUtil.checkEmail(username)){//邮箱登录
			user = loginService.emailLogin(username, password);
		}else{//用户名登录
			user = loginService.loginNameLogin(username, password);
		}
		if(user == null){
			result.setMessage(Result.LOGINERROR);
			result.setStatus(Result.FAILED);
			return result;
		}
		//
		user.setLastLoginDate(new Date());
		loginService.loginDate(user);
		request.getSession().setAttribute("u", user);
		result.setStatus(Result.SUCCESS);
		return result;
	}

	/**
	 * 登出
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/logout")
    @ResponseBody
	public Result logout(HttpSession httpSession){
        Result result = new Result();
		httpSession.invalidate();
		result.setStatus(0);
		return result;
	}
	
	
}
