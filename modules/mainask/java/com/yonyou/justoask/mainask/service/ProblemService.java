package com.yonyou.justoask.mainask.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.justoask.mainask.domain.*;
import com.yonyou.justoask.mainask.dao.mybatis.*;

import core.utils.ComUtil;

@Service
@Transactional
public class ProblemService {

	@Autowired
	private ProblemDao problemDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams ：查询条件
	 * @return List<Problem> ：查询结果集
	 */
	public List<Problem> searchByPage(Map<String,Object> searchParams) {
		return (List<Problem>) problemDao.searchByPage(searchParams);
	}
	
	/**
	 * 通过problemId获取一条记录
	 * @param problemId ：记录主键
	 * @return Problem ：记录对象实体
	 */
	public Problem findById(String problemId) {
		return problemDao.findById(problemId);
	}

	/**
	 * 保存一条记录
	 * @param problem ：记录对象实体
	 */
	public void save(Problem problem) {
		problem.setProblemId(ComUtil.getId());
		problemDao.save(problem);
	}

	/**
	 * 通过 problemId删除一条记录
	 * @param problemId ：记录主键ID
	 */
	public void delete(String problemIds) {
		for(String problemId:problemIds.split(",")){
			problemDao.delete(problemId);
		}
	}
	
	/**
	 * 修改记录
	 * @param problem ：记录对象实体
	 */
	public void update(Problem problem) {
		problemDao.update(problem);
	}
}
