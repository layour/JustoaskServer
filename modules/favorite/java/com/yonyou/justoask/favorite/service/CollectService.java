package com.yonyou.justoask.favorite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.justoask.favorite.domain.*;
import com.yonyou.justoask.favorite.dao.mybatis.*;

import core.utils.ComUtil;

@Service
@Transactional
public class CollectService {

	@Autowired
	private CollectDao collectDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams ：查询条件
	 * @return List<Collect> ：查询结果集
	 */
	public List<Collect> searchByPage(Map<String,Object> searchParams) {
		return (List<Collect>) collectDao.searchByPage(searchParams);
	}

	/**
	 * 保存一条记录
	 * @param collect ：记录对象实体
	 */
	public void save(Collect collect) {
		collect.setCollectId(ComUtil.getId());
		collectDao.save(collect);
	}

	/**
	 * 通过 collectId删除一条记录
	 * @param collectId ：记录主键ID
	 */
	public void delete(String collectIds) {
		for(String collectId:collectIds.split(",")){
			collectDao.delete(collectId);
		}
	}
}
