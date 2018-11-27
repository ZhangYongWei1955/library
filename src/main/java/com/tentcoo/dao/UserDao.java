package com.tentcoo.dao;

import com.tentcoo.entity.UserEntity;

public interface UserDao {
	public void addUser(UserEntity user);
	
	public UserEntity  queryUserByUserid(String UserId);
}
