package com.yonyou.justoask.register.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.justoask.register.domain.*;
import com.yonyou.justoask.register.dao.mybatis.*;

import core.utils.ComUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 按条件分页查询记录
	 * @param searchParams ：查询条件
	 * @return List<User> ：查询结果集
	 */
	public List<User> searchByPage(Map<String,Object> searchParams) {
		return (List<User>) userDao.searchByPage(searchParams);
	}
	
	/**
	 * 通过userId获取一条记录
	 * @param userId ：记录主键
	 * @return User ：记录对象实体
	 */
	public User findById(String userId) {
		return userDao.findById(userId);
	}

	/**
	 * 保存一条记录
	 * @param user ：记录对象实体
	 */
	public void save(User user) {
		user.setUserId(ComUtil.getId());
		userDao.save(user);
	}

	/**
	 * 通过 userId删除一条记录
	 * @param userId ：记录主键ID
	 */
	public void delete(String userIds) {
		for(String userId:userIds.split(",")){
			userDao.delete(userId);
		}
	}
	
	/**
	 * 修改记录
	 * @param user ：记录对象实体
	 */
	public void update(User user) {
		userDao.update(user);
	}
}
