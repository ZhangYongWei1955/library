package com.tentcoo.library.pojo;

public class BookLink {
	private String Link;
	private boolean isCategory;
	private String bookName;
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public boolean isCategory() {
		return isCategory;
	}
	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Override
	public String toString() {
		return "BookLink [Link=" + Link + ", isCategory=" + isCategory + ", bookName=" + bookName + "]";
	}
	
}
