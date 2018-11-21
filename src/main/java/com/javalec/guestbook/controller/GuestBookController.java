package com.javalec.guestbook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.guestbook.dao.GuestbookDao;
import com.javalec.guestbook.service.IGuestBookService;
import com.javalec.guestbook.vo.GuestbookVo;

@Controller("guestbook")
public class GuestBookController {

	@Autowired
	private IGuestBookService guestbookService ;
	
	
	
	@RequestMapping(value="/insert.do")
	public String insertGuestBook(GuestbookVo vo) {
		guestbookService.insertGuestBook(vo);
		return "getguestbooklist.do" ;
	}
	
	@RequestMapping("/deleteform.do")
	public String deleteForm() {
		System.out.println("삭제폼 이동 수행");
		return "deleteform.jsp" ; 
	}
	
	@RequestMapping("/delete.do")
	public String deleteGuestBook(GuestbookVo vo) {
		System.out.println("삭제 작업 수행");
		guestbookService.deleteGuestBook(vo);
		return "getguestbooklist.do" ;
	}

	                  
	@RequestMapping("/getguestbooklist.do")
	public String getGuestBookList(GuestbookDao dao, Model model) {
	System.out.println("보드 리스트 조회");
	List<GuestbookVo> list =   guestbookService.getGuestBookList() ;
	model.addAttribute("list", list) ;
	return "index.jsp";
}
	
	@RequestMapping("/searchkeywordlist.do")
	public String getGuestBookList(GuestbookDao dao, Model model, @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword) {
		System.out.println("검색 키드 :" + keyword);

	List<GuestbookVo> list =   guestbookService.getGuestBookKeywordList(keyword) ;
	
	model.addAttribute("list", list) ;
	model.addAttribute("searchKeyword", keyword) ;
	return "index.jsp";
	}	

}
