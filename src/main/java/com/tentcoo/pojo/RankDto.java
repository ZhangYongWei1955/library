package com.tentcoo.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value="RankDto")
public class RankDto {
	private String keyWorld;
	private String times;
	public String getKeyWorld() {
		return keyWorld;
	}
	public void setKeyWorld(String keyWorld) {
		this.keyWorld = keyWorld;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
}
