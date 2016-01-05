package com.yonyou.justoask.register.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.yonyou.justoask.register.domain.*;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface UserDao {
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams ：查询条件
	 * @return List<User> ：查询结果集
	 */
	List<User> searchByPage(Map<String,Object> searchParams);
	
	/**
	 * 通过 userId获取一条记录
	 * @param  userId ：记录主键
	 * @return User ：记录对象实体
	 */
	User findById(String userId);
	
	/**
	 * 保存一条记录
	 * @param user ：记录对象实体
	 */
	void save(User user);
	
	/**
	 * 通过user删除一条记录
	 * @param user ：记录主键ID
	 */
	void delete(String userId);
	
	/**
	 * 修改记录
	 * @param user ：记录对象实体
	 */
	void update(User user);
}