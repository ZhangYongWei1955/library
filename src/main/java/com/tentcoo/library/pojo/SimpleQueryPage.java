package com.tentcoo.library.pojo;

import java.util.List;

public class SimpleQueryPage {
	private int currentStart;
	private boolean hasNaxtPage;
	private boolean hasPrevPage;
	private List<BookLink> books;
	public int getCurrentStart() {
		return currentStart;
	}
	public void setCurrentStart(int currentStart) {
		this.currentStart = currentStart;
	}
	public boolean isHasNaxtPage() {
		return hasNaxtPage;
	}
	public void setHasNaxtPage(boolean hasNaxtPage) {
		this.hasNaxtPage = hasNaxtPage;
	}
	public boolean isHasPrevPage() {
		return hasPrevPage;
	}
	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}
	public List<BookLink> getBooks() {
		return books;
	}
	public void setBooks(List<BookLink> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "SimpleQueryPage [currentStart=" + currentStart + ", hasNaxtPage=" + hasNaxtPage + ", hasPrevPage="
				+ hasPrevPage + ", books=" + books + "]";
	}
	
}
