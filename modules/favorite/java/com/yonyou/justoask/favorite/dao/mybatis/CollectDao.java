package com.yonyou.justoask.favorite.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.yonyou.justoask.favorite.domain.*;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface CollectDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams ：查询条件
	 * @return List<Collect> ：查询结果集
	 */
	List<Collect> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 保存一条记录
	 * @param collect ：记录对象实体
	 */
	void save(Collect collect);
	
	/**
	 * 通过collect删除一条记录
	 * @param collect ：记录主键ID
	 */
	void delete(String collectId);
}