package com.tentcoo.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.tentcoo.dao.*;
import com.tentcoo.entity.BookListEntity;
import com.tentcoo.entity.RecordEntity;
import com.tentcoo.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Resource
	BookListDao bookListDao;
	
	@Resource
	RecordDao recordDao;
	
	@Override
	public void addBookList(BookListEntity bookList) {
		 bookListDao.addBookList(bookList);
	}

	@Override
	public void updateBookList(BookListEntity bookList) {
		bookListDao.updateBookList(bookList);
	}

	@Override
	public List<BookListEntity> selectByUserId(String userId) {
		return bookListDao.selectByUserId(userId);
	}

	@Override
	public void addRecord(RecordEntity record) {
		record.setSearchTime(new Date());
		recordDao.addRecord(record);
	}

	@Override
	public List<RecordEntity> getRankList() {
		return recordDao.getRankList();
	}

}
