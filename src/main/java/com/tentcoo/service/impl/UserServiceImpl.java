package com.tentcoo.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.tentcoo.dao.UserDao;
import com.tentcoo.entity.UserEntity;
import com.tentcoo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	UserDao userDao;
	
	@Override
	public void addUser(UserEntity user) {
		userDao.addUser(user);
	}

	@Override
	public UserEntity queryUserByUserid(String UserId) {
		return userDao.queryUserByUserid(UserId);
	}

}
