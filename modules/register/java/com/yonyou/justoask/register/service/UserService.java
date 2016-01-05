package com.yonyou.justoask.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.justoask.register.dao.mybatis.UserDao;
import com.yonyou.justoask.register.domain.User;

import core.utils.ComUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 通过userId获取一条记录
	 * @param userId ：记录主键
	 * @return User ：记录对象实体
	 */
	public User findById(String userId) {
		return userDao.findById(userId);
	}
	
	/**
	 * 通过phoneNo获取一条记录
	 * @param userName 唯一约束
	 * @return
	 */
	public User findByUserName(String userName) {
		User user = userDao.findByUserName(userName);
		if(user == null){
			user = new User();
		}
		return user;
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
	 * 修改记录
	 * @param user ：记录对象实体
	 */
	public void update(User user) {
		userDao.update(user);
	}
}
