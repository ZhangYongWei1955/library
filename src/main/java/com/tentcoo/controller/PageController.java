package com.tentcoo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tentcoo.entity.BookListEntity;
import com.tentcoo.entity.RecordEntity;
import com.tentcoo.entity.UserEntity;
import com.tentcoo.library.pojo.BookDetail;
import com.tentcoo.platform.UserComponent;
import com.tentcoo.service.*;

@Controller
public class PageController {
	
	@Resource
	LibraryService libraryService;
	@Resource
	UserService userService;
	@Resource
	BookService bookService;
	
	@Resource
	UserComponent userComponent;
	
	@RequestMapping(value= {"","/","index.html"})
	public String getIndexPage(String ticket,HttpServletRequest request,HttpServletResponse response) throws IOException{
		if(request.getSession().getAttribute("isLogin")==null) {
			if(ticket != null && ticket.isEmpty()) {
				Map<String,String> userRes = userComponent.getUserInfo(ticket);
				if(userRes != null && !userRes.isEmpty()) {
					String userID = userRes.get("userid");
					if(userID != null) {
						request.getSession().setAttribute("isLogin", true);
						request.getSession().setAttribute("userId", userRes.get("userid"));
						UserEntity resultUser = userService.queryUserByUserid(userID);
						if(resultUser != null) {
							UserEntity user = new UserEntity();
							user.setKingdeeId(userRes.get("userid"));
							user.setMemberName(userRes.get("mobile"));
							user.setUserNumber(userRes.get("username"));
							userService.addUser(user);
						}
					}else {
						response.sendError(500);
					}
				}else {
					response.sendError(403);
				}
			}else {
				response.sendError(403);
			}
		}
		
		return "index";
	}
	
	@RequestMapping(value="search.html")
	public String getSearchPage(HttpServletRequest request) {
		return "search";
	}
	
	@RequestMapping(value="result.html")
	public String getResultPage(String word,HttpServletRequest request) {
		request.setAttribute("word", word);
		String userId = (String) request.getSession().getAttribute("userId");
		UserEntity user = userService.queryUserByUserid(userId);
		if(word != null && !word.isEmpty()) {
			RecordEntity record = new RecordEntity();
			record.setKeyword(word);
			record.setUser(user);
			record.setUserAgent(request.getHeader("user-agent"));
			bookService.addRecord(record);
		}
		return "result";
	}
	
	@RequestMapping(value="detail.html")
	public String getDetailPage(String spm,HttpServletRequest request)throws Exception {
		byte[] baseByte = Base64.decodeBase64(spm);
		String query = new String(baseByte, "utf-8");
		boolean showAddBtn = true;
		String userId = (String) request.getSession().getAttribute("userId");
		UserEntity user = userService.queryUserByUserid(userId);
		if (query.startsWith("tentcoo_") && query.endsWith("$$tentcoo_end")) {
			query = "http://query.libmill.com" + query.replace("tentcoo_", "").replace("$$tentcoo_end", "");
			BookDetail detail  = libraryService.getBookDetail(query);
			List<BookListEntity> bookList = bookService.selectByUserId(user.getId());
			for(BookListEntity bookListItem : bookList ) {
				if(bookListItem.getBookNumber().equals(detail.getLibNumber())) {
					showAddBtn  = false;
					break;
				}
			}
			request.setAttribute("showAddBtn", showAddBtn);
			request.setAttribute("book", detail);
		}
		return "detail";
	}
	
	@RequestMapping(value="category.html")
	public String getCategoryPage(String spm,HttpServletRequest request)throws Exception{
		byte[] baseByte = Base64.decodeBase64(spm);
		String query = new String(baseByte, "utf-8");
		if (query.startsWith("tentcoo_") && query.endsWith("$$tentcoo_end")) {
			query = "http://query.libmill.com" + query.replace("tentcoo_", "").replace("$$tentcoo_end", "");
			request.setAttribute("category", libraryService.queryCategory(query));
		}
		return "category";
	}
	
	@RequestMapping(value="mine.html")
	public String getMinePage(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		UserEntity user = userService.queryUserByUserid(userId);
		List<BookListEntity> bookList = bookService.selectByUserId(user.getId());
		request.setAttribute("myBookList", bookList);
		return "mine";
	}
	
	@RequestMapping(value="rank.html")
	public String getRankPage(HttpServletRequest request) {
		request.setAttribute("rankList", bookService.getRankList());
		return "rank";
	}
	
}
