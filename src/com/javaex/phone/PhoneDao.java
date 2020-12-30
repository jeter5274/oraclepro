package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
	
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private int count=0;
	private String allSelectQuery = "select person_id, name, hp, company from person";
	private String searchWord=null;

	//생성자
	
	//메소드 -getter/setter
	
	//메소드 일반
	
	private void getConnetion() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	private void resourceClose() {
		try {
	        if (rs != null) {
	            rs.close();
	        }            	
	    	if (pstmt != null) {
	        	pstmt.close();
	        }
	    	if (conn != null) {
	        	conn.close();
	        }
	    } catch (SQLException e) {
	    	System.out.println("error:" + e);
	    }
	}
	
	public List<PhoneVo> getPersonList() {
		
		List<PhoneVo> phoneVoList = new ArrayList<PhoneVo>();
		
		getConnetion();
		
		try {	
			// 3. SQL문 준비 / 바인딩 / 실행
			/*
			String query = "";
			query += " select	person_id,";
			query += "			name,";
			query += "			hp,";
			query += "			company";
			query += " from person";
			*/
			
			//검색 개선
			if(searchWord != null) {
				
				String query = allSelectQuery;
				query += " where name like ?";
				query += " or hp like ?";
				query += " or company like ?";
				query += " order by person_id asc";
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, searchWord);
				pstmt.setString(2, searchWord);
				pstmt.setString(3, searchWord);
				
				searchWord = null;
				
			}else {
				
				String query = allSelectQuery;
				query += " order by person_id asc";
				
				pstmt = conn.prepareStatement(query);
			}
			
			rs = pstmt.executeQuery();
	        
			// 4.결과처리
			while(rs.next()){
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
				phoneVoList.add(phoneVo);
			}
		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		resourceClose();
		
		return phoneVoList;
	}

	public int phoneInsert(String name, String hp, String company) {
		
		getConnetion();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person";
			query += " values (seq_person_id.nextval, ?, ?, ?)";
			 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[" +count+ "건 등록되었습니다.]");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		resourceClose();
		
		return count;
	}
	
	public int phoneUpdate(int personId, String name, String hp, String company) {
		
		getConnetion();
		
		try {	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person";
			query += " set 	name = ?,";
			query += " 		hp = ?,";
			query += " 		company = ?";
			query += " where person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);
			pstmt.setInt(4, personId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[" +count+ "건 수정되었습니다.]");

			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		resourceClose();
		
		return count;
	}
	
	public int phoneDelete(int personId) {
		
		getConnetion();
		
		try {	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete person";
			query += " where person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, personId);
			
			count = pstmt.executeUpdate();
		
			// 4.결과처리
			System.out.println("[" +count+ "건 삭제되었습니다.]");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		resourceClose();
		
		return count;
	}

	//검색 개선 - getPersonList활용
	public List<PhoneVo> phoneSearch(String searchWord) {	
		
		this.searchWord = "%" + searchWord + "%";
		
		return getPersonList();
	}

	/* 개선전 검색기능
	public List<PhoneVo> phoneSearch(String searchWord) {
		
		List<PhoneVo> phoneVoList = new ArrayList<PhoneVo>();
		
		getConnetion();
		
		searchWord = "%" + searchWord + "%";
		
		try {	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select	person_id,";
			query += "			name,";
			query += "			hp,";
			query += "			company";
			query += " from person";
			query += " where name like ?";
			query += " or hp like ?";
			query += " or company like ?";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, searchWord);
			pstmt.setString(2, searchWord);
			pstmt.setString(3, searchWord);
			
			rs = pstmt.executeQuery();
	        
			// 4.결과처리
			while(rs.next()){
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, name, hp, company);
				phoneVoList.add(phoneVo);
			}
		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		resourceClose();
		
		return phoneVoList;
	}
	*/
}
