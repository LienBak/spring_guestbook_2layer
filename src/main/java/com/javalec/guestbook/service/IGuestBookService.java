package com.javalec.guestbook.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.guestbook.dao.GuestbookDao;
import com.javalec.guestbook.vo.GuestbookVo;

public interface IGuestBookService {

	public void insertGuestBook(GuestbookVo vo)  ;
	public void deleteGuestBook(GuestbookVo vo) ;
	public List<GuestbookVo> getGuestBookList()  ;
	public List<GuestbookVo> getGuestBookKeywordList(String keyword) ;
	
}
