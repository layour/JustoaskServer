package com.yonyou.justoask.register.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.justoask.register.domain.User;
import com.yonyou.justoask.register.service.UserService;

import core.spring.RequestMappingName;
import core.utils.jackson.JacksonUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 登录验证
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = "登录")
	@RequestMapping(value="login", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String login(Model model, ServletRequest request) {
		
		String loginName = request.getParameter("loginName");//获取用户登录账号
		String loginPwd = request.getParameter("loginPwd");//获取用户登录密码
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		User user = userService.findByUserName(loginName);
		if(loginPwd.equals(user.getPassword())){
			result.put("code", "0");
			result.put("msg", "登录成功");
			result.put("user", user);
		} else if(user.getUserId() == null){
			result.put("code", "1");
			result.put("msg", "无此用户");
		} else {
			result.put("code", "2");
			result.put("msg", "用户名或密码错误");
		}
		
		return new JacksonUtil().getJson(result);
	}
	
	/**
	 * 执行保存操作
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMappingName(value = "移动保存")
	@RequestMapping(value="mobileSave", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")  
	@ResponseBody
	public String mobileSave(@Valid User user, Model model) {
		userService.save(user);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "0");
		result.put("msg", "注册成功");
		result.put("user", user);
		
		return new JacksonUtil().getJson(result);
	}
}
