package com.javalec.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.javalec.guestbook.vo.GuestbookVo;



@Repository
public class GuestbookJDBCDao {

	
	
	private String guestBookInsert ="insert into guestbook values "
			+ "(guestbook_seq.nextval, ?, ?, ?, sysdate)";
	private String guestBookDelete="delete from guestbook where no = ? and password = ?";
	private String guestBookList = "select no, name, content, password, "
			+ "to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' ) regdate from guestbook order by reg_date desc"; 
		
	private String guestBookKeywordList =
			"   select no, name, content, password, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' ) regdate" +
			"     from guestbook  where content like ? " +
			" order by reg_date desc";
	
	 
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	
	
	public void delete(GuestbookVo vo) {
		System.out.println("GuestBook 삭제");
		jdbcTemplate.update(guestBookDelete, vo.getNo(), vo.getPassword());
	}
	

	
	public void insert(GuestbookVo vo) {
		System.out.println("GuestBook 입력");
			jdbcTemplate.update(guestBookInsert, vo.getName(), vo.getContent(), vo.getPassword());
	}	
	
	
	//리스트 조회ㅏ
	public List<GuestbookVo> getList(){
			System.out.println("GuestBook 리스트 조회");
			
			return jdbcTemplate.query(guestBookList, new GuestBookMapper()) ;
		}
		//키워드 리스트 조회
		public List<GuestbookVo> getKeywordList(String keyword){
			System.out.println("GuestBook 키워드 리스트 조회");
			Object [] args = {"%"+keyword +"%"} ;
			return jdbcTemplate.query(guestBookKeywordList, args,  new GuestBookMapper()) ;
		}
}
