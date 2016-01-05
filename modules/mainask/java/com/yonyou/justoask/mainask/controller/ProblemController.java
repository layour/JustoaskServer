package com.yonyou.justoask.mainask.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.yonyou.justoask.mainask.domain.*;
import com.yonyou.justoask.mainask.service.*;

import core.mybatis.PageParameter;
import core.spring.RequestMappingName;
import core.utils.jackson.JacksonUtil;

@Controller
@RequestMapping(value = "/problem")
public class ProblemController {

	private static Logger logger = LoggerFactory.getLogger(ProblemController.class);
	
	@Autowired
	private ProblemService problemService;
	
	/**
	 * 跳转到管理页面
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "跳转到管理页面")
	@RequestMapping(value = "toManagerPage", method = RequestMethod.GET)
	public String toManagerPage() {
		return "mainask/problem/problemList";
	}
	
	/**
	 * 执行查询列表操作（分布查询）
	 * @param pageNumber ：当前页号（默认为常量）
	 * @param pageSize ：页记录数（默认为常量）
	 * @param model ：视图对象（参数传递）
	 * @param request ：请求对象（收集客户端参数）
	 * @return @ResponseBody ：列表json格式文本串
	 */
	@RequestMappingName(value = "执行查询列表操作")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public @ResponseBody String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int pageSize, 
			Model model, ServletRequest request) {
		logger.info("处理查询条件值，并放入Map对象中...");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		
		List<Problem> problems = problemService.searchByPage(searchParams);
		
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("rows", problems);
		
		return new JacksonUtil().getJson(model);
	}
	
	/**
	 * 跳转到增加页面
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "跳转到增加页面")
	@RequestMapping(value = "toAddPage", method = RequestMethod.GET)
	public String toAddPage() {
		return "mainask/problem/problemAdd";
	}
	
	/**
	 * 执行保存操作
	 * @param problem ：pojo对象
	 * @param redirectAttributes ：跳转参数设置对象
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "执行保存操作")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Problem problem, RedirectAttributes redirectAttributes) {
		problemService.save(problem);
		return "mainask/problem/problemList";
	}
	
	/**
	 * 跳转到修改页面
	 * @param problemId ：记录ID键值
	 * @param model ：视图对象（参数传递）
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "跳转到修改页面")
	@RequestMapping(value = "toUpdatePage/{problemId}", method = RequestMethod.GET)
	public String toUpdatePage(@PathVariable("problemId") String problemId, Model model) {
		model.addAttribute("problem", problemService.findById(problemId));
		return "mainask/problem/problemUpdate";
	}
	
	/**
	 * 执行修改操作
	 * @param problem ：pojo对象
	 * @param redirectAttributes ：跳转参数设置对象
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "执行修改操作")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("problem") Problem problem, RedirectAttributes redirectAttributes) {
		problemService.update(problem);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "mainask/problem/problemList";
	}
	
	/**
	 * 跳转到详细页面
	 * @param problemId ：记录ID键值
	 * @param model  ：视图对象（参数传递）
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "跳转到详细页面")
	@RequestMapping(value = "toDetailPage/{problemId}", method = RequestMethod.GET)
	public String toDetailPage(@PathVariable("problemId") String problemId, Model model) {
		model.addAttribute("problem", problemService.findById(problemId));
		return "mainask/problem/problemDetail";
	}
	 
	/**
	 * 执行删除操作
	 * @param problemId ：记录ID键值
	 * @param redirectAttributes ：跳转参数设置对象
	 * @return String ：跳转路径
	 */
	@RequestMappingName(value = "删除")
	@RequestMapping(value = "delete/{problemId}")
	public String delete(@PathVariable("problemId") String problemId, RedirectAttributes redirectAttributes) {
		problemService.delete(problemId);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "mainask/problem/problemList";
	}
}
