package com.tentcoo.dao;

import java.util.List;

import com.tentcoo.entity.RecordEntity;

public interface RecordDao {
	
	public void addRecord(RecordEntity record);
	
	public List<RecordEntity> getRankList();
}
