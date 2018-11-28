package com.tentcoo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tentcoo.entity.BookListEntity;
import com.tentcoo.entity.UserEntity;
import com.tentcoo.service.BookService;
import com.tentcoo.service.UserService;

@Controller
public class UserController {
	
	@Resource
	UserService  userService;
	@Resource
	BookService bookService;
	
	
	public Map<String,Object> addBookList(String code,String name,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		if(code == null || code.isEmpty()) {
			map.put("status", "error");
			return map;
		}
		if(name == null || name.isEmpty()) {
			map.put("status", "error");
			return map;
		}
		String userId = (String)request.getSession().getAttribute("userId");
		UserEntity user = userService.queryUserByUserid(userId);
		BookListEntity bookList = new BookListEntity();
		bookList.setBookName(name);
		bookList.setBookNumber(code);
		bookList.setUser(user);
		bookList.setMarkTime(new Date());
		bookService.addBookList(bookList);
		map.put("status", "ok");
		return map;
	}
	
	@RequestMapping(value="delete_book_list_item")
	@ResponseBody
	public Map<String,Object> deleteBookList(String id,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String userId = (String) request.getSession().getAttribute("userId");
		UserEntity user = userService.queryUserByUserid(userId);
		List<BookListEntity> bookList = bookService.selectByUserId(user.getId());
		if(user!=null && bookList!=null) {
			for(BookListEntity booklist : bookList ) {
				if(booklist.getId()!=null && booklist.getId().equals(id)) {
					booklist.setDeleteTime(new Date());
					booklist.setIsDelete(1);
					if(booklist.getUser()==null) {
						booklist.setUser(user);
					}
					bookService.updateBookList(booklist);
					break;
				}
			}
		}else {
			map.put("status", "error");
		}map.put("status", "ok");
		return map;
	}
	
}
