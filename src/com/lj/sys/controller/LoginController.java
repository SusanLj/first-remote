package com.lj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lj.sys.constast.SysConstast;
import com.lj.sys.domain.User;
import com.lj.sys.service.UserService;
import com.lj.sys.utils.WebUtils;
import com.lj.sys.vo.UserVo;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	//跳转到登录页面
	@RequestMapping("toLogin")
	public String toLogin() {
		return "system/main/login";
	}
	
	
	//登录方法
	@RequestMapping("login")
	public String login(UserVo userVo,Model model) {
		
		User user=userService.login(userVo);
		if(user!=null) {
			//放到session中
			WebUtils.getHttpSession().setAttribute("user", user);
			//记录登录日志 向sys_login_log里面插入数据
			
			return "system/main/index";
			
		}else {
			model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
			return "system/main/login";
		}
		
	}
	
	
}
