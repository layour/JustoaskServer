package com.yonyou.justoask.mainask.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yonyou.justoask.mainask.domain.Problem;
import com.yonyou.justoask.mainask.service.ProblemService;
import com.yonyou.justoask.mainask.util.JSoupBaiduSearcher;
import com.yonyou.justoask.mainask.util.SearchResult;
import com.yonyou.justoask.mainask.util.Searcher;
import com.yonyou.justoask.mainask.util.Webpage;

import core.spring.RequestMappingName;
import core.utils.jackson.JacksonUtil;

@Controller
@RequestMapping(value = "/problem")
public class ProblemController {

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
		
		String keyword = request.getParameter("keyword");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Searcher searcher = new JSoupBaiduSearcher();
        SearchResult searchResult = searcher.search(keyword, 1);
        List<Webpage> webpages = searchResult.getWebpages();
        if (webpages != null) {
        	int i = 1;
        	StringBuffer askStr = new StringBuffer();
        	askStr.append("共有搜索到" + searchResult.getTotal() + "条结果，已为您挑选出3条结果。");
            for (Webpage webpage : webpages) {
            	askStr.append("结果" + (i++) + "：");
            	askStr.append("标题：" + webpage.getTitle());
            	askStr.append("摘要：" + webpage.getSummary());
            }
            result.put("code", "0");
            result.put("msg", "搜索成功");
            result.put("result", askStr.toString());
        } else {
        	result.put("code", "1");
        	result.put("msg", "没有搜索到结果");
        }
		
//		SearchResult user = problemService.findByKeyWord(keyword);
//		if(password.equals(user.getPassword())){
//			result.put("code", "0");
//			result.put("msg", "登录成功");
//			result.put("user", user);
//		} else if(user.getUserId() == null){
//			result.put("code", "1");
//			result.put("msg", "无此用户");
//		} else {
//			result.put("code", "2");
//			result.put("msg", "用户名或密码错误");
//		}
		
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
