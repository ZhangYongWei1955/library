package com.tentcoo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.tentcoo.dao.BookListDao;
import com.tentcoo.entity.BookListEntity;
import com.tentcoo.utils.UUIDGenerator;

@Repository
public class BookListDaoImpl implements BookListDao{

	@Resource
	SqlSessionTemplate sqlSessionTemplate;
	
	@Resource
	UUIDGenerator uuidGenerator;
	
	@Override
	public void addBookList(BookListEntity bookList) {
		bookList.setId(uuidGenerator.generateUUID());
		sqlSessionTemplate.insert("BookListDao.addBookList",bookList);
	}

	@Override
	public void updateBookList(BookListEntity bookList) {
		sqlSessionTemplate.update("BookListDao.updateBookList", bookList);
	}

	@Override
	public List<BookListEntity> selectByUserId(String userId) {
		return sqlSessionTemplate.selectList("BookListDao.selectByUserId", userId);
	}

}
