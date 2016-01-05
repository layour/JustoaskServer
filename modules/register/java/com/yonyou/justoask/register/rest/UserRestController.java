package com.yonyou.justoask.register.rest;

import java.net.URI;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

import core.rest.RestException;

import com.yonyou.justoask.register.domain.*;
import com.yonyou.justoask.register.service.UserService;

/**
 * Restful API的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserRestController {

	private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private Validator validator;
	
	/**
	 * 获取单个记录
	 * @param userId ：记录主键ID
	 * @return User ：实体对象
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public User get(@PathVariable("userId") String userId) {
		User user = userService.findById(userId);
		if (user == null) {
			String message = "不存在(userId:" + userId + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return user;
	}
	
	/**
	 * 新增记录
	 * @return ResponseEntity<?> ：重定向URL
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody User user, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, user);
		// 保存任务
		userService.save(user);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回userId或对象.
		String userId = user.getUserId();
		URI uri = uriBuilder.path("/api/user/" + userId).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	/**
	 * 修改记录
	 * @param userId ：记录主键ID
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody User user) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, user);
		// 保存任务
		userService.update(user);
	}

	/**
	 * 删除记录
	 * @param userId ：记录主键ID
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("userId") String userId) {
		userService.delete(userId);
	}
}
