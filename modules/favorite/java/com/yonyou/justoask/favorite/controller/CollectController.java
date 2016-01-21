package com.yonyou.justoask.favorite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.yonyou.justoask.favorite.domain.Collect;
import com.yonyou.justoask.favorite.service.CollectService;
import com.yonyou.justoask.favorite.util.DateTimeUtil;
import com.yonyou.justoask.mainask.domain.Problem;
import com.yonyou.justoask.mainask.service.ProblemService;

import core.mybatis.PageParameter;
import core.spring.RequestMappingName;
import core.utils.jackson.JacksonUtil;

@Controller
@RequestMapping(value = "/collect")
public class CollectController {

	private static Logger logger = LoggerFactory.getLogger(CollectController.class);
	
	@Autowired
	private CollectService collectService;
	
	@Autowired
	private ProblemService problemService;
	
	/**
	 * 执行查询列表操作（分布查询）
	 * @param pageNumber ：当前页号（默认为常量）
	 * @param pageSize ：页记录数（默认为常量）
	 * @param model ：视图对象（参数传递）
	 * @param request ：请求对象（收集客户端参数）
	 * @return @ResponseBody ：列表json格式文本串
	 */
	@RequestMappingName(value = "执行查询列表操作")
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public @ResponseBody String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int pageSize, 
			Model model, ServletRequest request) {
		logger.info("处理查询条件值，并放入Map对象中...");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		
		List<Collect> collects = collectService.searchByPage(searchParams);
		
		model.addAttribute("total", pageSize * pageNumber * 10);
		model.addAttribute("rows", collects);
		
		return new JacksonUtil().getJson(model);
	}
	
	/**
	 * 执行保存操作
	 * @param collect ：pojo对象
	 * @param redirectAttributes ：跳转参数设置对象
	 * @param request ：请求对象（收集客户端参数）
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "执行保存操作")
	@RequestMapping(value="save", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public String save(@Valid Collect collect, RedirectAttributes redirectAttributes, ServletRequest request) {
		String dateTime = DateTimeUtil.getDateTime();
		
		String problemDesc = request.getParameter("problem");
		String answer = request.getParameter("answer");
		Problem problem = new Problem();
		problem.setProblem(problemDesc);
		problem.setAnswer(answer);
		problemService.save(problem);
		
		String userId = request.getParameter("userId");
		collect.setCollectTime(dateTime);
		collectService.save(collect);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "0");
		result.put("msg", "收藏成功");
		result.put("user", collect);
		
		return new JacksonUtil().getJson(result);
	}
	 
	/**
	 * 执行删除操作
	 * @param collectId ：记录ID键值
	 * @param redirectAttributes ：跳转参数设置对象
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "删除")
	@RequestMapping(value = "delete/{collectId}")
	public String delete(@PathVariable("collectId") String collectId, RedirectAttributes redirectAttributes) {
		collectService.delete(collectId);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "favorite/collect/collectList";
	}
}
