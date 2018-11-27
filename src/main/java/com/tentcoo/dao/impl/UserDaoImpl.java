package com.tentcoo.dao.impl;

import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.tentcoo.dao.UserDao;
import com.tentcoo.entity.UserEntity;
import com.tentcoo.utils.UUIDGenerator;

@Repository
public class UserDaoImpl implements UserDao{

	@Resource
	SqlSessionTemplate sqlSessionTemplate;
	
	@Resource
	UUIDGenerator uuidGenerator;
	
	@Override
	public void addUser(UserEntity user) {
		user.setId(uuidGenerator.generateUUID());
		sqlSessionTemplate.insert("UserDao.addUser",user);
	}

	@Override
	public UserEntity queryUserByUserid(String userId) {
		
		return sqlSessionTemplate.selectOne("UserDao.queryUserByUserid",userId);
	}

}
