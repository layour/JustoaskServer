package com.yonyou.justoask.mainask.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.yonyou.justoask.favorite.controller.CollectController;
import com.yonyou.justoask.mainask.domain.Problem;
import com.yonyou.justoask.mainask.service.ProblemService;
import com.yonyou.justoask.mainask.util.JSoupBaiduSearcher;
import com.yonyou.justoask.mainask.util.SearchResult;
import com.yonyou.justoask.mainask.util.Searcher;
import com.yonyou.justoask.mainask.util.Webpage;

import core.mybatis.PageParameter;
import core.spring.RequestMappingName;
import core.utils.jackson.JacksonUtil;

@Controller
@RequestMapping(value = "/problem")
public class ProblemController {
	
	private static Logger logger = LoggerFactory.getLogger(CollectController.class);

	@Autowired
	private ProblemService problemService;
	
	/**
	 * 问题搜索
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = "问题搜索")
	@RequestMapping(value="search", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String search(Model model, ServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		StringBuffer askStr = new StringBuffer();
		
		String keyword = request.getParameter("keyword");//获取
		
		logger.info("处理查询条件值，并放入Map对象中...");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("problemDesc", keyword.replace("。", ""));
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(1);
		page.setPageSize(3);
		searchParams.put("page", page);
		
		int conut = 0;
		List<Problem> searchByPage = problemService.searchByPage(searchParams);
		if(searchByPage.size() <= 0){
			//百度搜索
			Searcher searcher = new JSoupBaiduSearcher();
			SearchResult searchResult = searcher.search(keyword, 1);
			List<Webpage> webpages = searchResult.getWebpages();
			conut = webpages.size();
			if (webpages != null) {
				int i = 1;
				askStr.append("共搜索到" + searchResult.getTotal() + "条答案。");
				for (Webpage webpage : webpages) {
					askStr.append("答案" + (i++) + "：");
					//askStr.append("标题：" + webpage.getTitle());
					//askStr.append("摘要：" + webpage.getSummary());
					//askStr.append("内容：" + webpage.getContent());
					askStr.append(webpage.getSummary() + "&&");
				}
			}
		} else {
			conut = searchByPage.size();
			for (Problem problem : searchByPage) {
				askStr.append(problem.getAnswer());
				askStr.append("&&");
			}
		}
		
        if(askStr.toString() != null){
            result.put("code", "0");
            result.put("msg", "搜索成功");
            result.put("result", askStr);
            result.put("conut", conut);
        } else {
        	result.put("code", "1");
        	result.put("msg", "没有搜索到结果");
        }
		
		return new JacksonUtil().getJson(result);
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
}
