package com.yonyou.justoask.mainask.rest;

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

import com.yonyou.justoask.mainask.domain.*;
import com.yonyou.justoask.mainask.service.ProblemService;

/**
 * Restful API的Controller.
 * 
 */
@Controller
@RequestMapping(value = "/api/problem")
public class ProblemRestController {

	private static Logger logger = LoggerFactory.getLogger(ProblemRestController.class);

	@Autowired
	private ProblemService problemService;

	@Autowired
	private Validator validator;
	
	/**
	 * 获取单个记录
	 * @param problemId ：记录主键ID
	 * @return Problem ：实体对象
	 */
	@RequestMapping(value = "/{problemId}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Problem get(@PathVariable("problemId") String problemId) {
		Problem problem = problemService.findById(problemId);
		if (problem == null) {
			String message = "不存在(problemId:" + problemId + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return problem;
	}
	
	/**
	 * 新增记录
	 * @return ResponseEntity<?> ：重定向URL
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody Problem problem, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, problem);
		// 保存任务
		problemService.save(problem);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回problemId或对象.
		String problemId = problem.getProblemId();
		URI uri = uriBuilder.path("/api/problem/" + problemId).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	/**
	 * 修改记录
	 * @param problemId ：记录主键ID
	 */
	@RequestMapping(value = "/{problemId}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Problem problem) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, problem);
		// 保存任务
		problemService.update(problem);
	}

	/**
	 * 删除记录
	 * @param problemId ：记录主键ID
	 */
	@RequestMapping(value = "/{problemId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("problemId") String problemId) {
		problemService.delete(problemId);
	}
}
