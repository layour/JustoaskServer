package com.yonyou.justoask.register.dao.mybatis;

import com.yonyou.justoask.register.domain.User;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface UserDao {
	
	/**
	 * 通过 userName 获取一条记录
	 * @param userName 主键
	 * @return
	 */
	User findByUserName(String userName);
	
	/**
	 * 保存一条记录
	 * @param user ：记录对象实体
	 */
	void save(User user);
	
	/**
	 * 修改记录
	 * @param user ：记录对象实体
	 */
	void update(User user);
}