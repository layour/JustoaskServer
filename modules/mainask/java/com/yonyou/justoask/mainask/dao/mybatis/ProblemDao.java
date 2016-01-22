package com.yonyou.justoask.mainask.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.yonyou.justoask.mainask.domain.*;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface ProblemDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams ：查询条件
	 * @return List<Problem> ：查询结果集
	 */
	List<Problem> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 通过 problemId获取一条记录
	 * @param  problemId ：记录主键
	 * @return Problem ：记录对象实体
	 */
	Problem findById(String problemId);
	
	/**
	 * 保存一条记录
	 * @param problem ：记录对象实体
	 */
	void save(Problem problem);
}