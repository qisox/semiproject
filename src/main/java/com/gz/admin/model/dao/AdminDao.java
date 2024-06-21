package com.gz.admin.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;
import com.gz.member.model.vo.Member;

public class AdminDao {
	
	private Properties prop = new Properties();
	
	public AdminDao() {
		
		String filePath = AdminDao.class.getResource("/db/sql/admin-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//회원 개수 조회
	public int listCount(Connection conn) {
		int count = 0;
		ResultSet rset = null;
		Statement stmt = null;
		
		String sql = prop.getProperty("listCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return count;
	}
	
	//회원 목록 조회
	public ArrayList<Member> selectUserList(Connection conn, PageInfo pi) {

		ArrayList<Member> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectUserList");
		
		int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage()*pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
				
			while(rset.next()) {
				
				list.add(new Member(rset.getInt("MEMBER_NO")
									,rset.getString("MEMBER_ID")
									,rset.getString("MEMBER_NAME")
									,rset.getString("MEMBER_NICKNAME")
									,rset.getString("MEMBER_EMAIL")
									,rset.getDate("MEMBER_ENROLL_DATE")
									,rset.getString("MEMBER_GRADE")
									,rset.getString("STATUS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	//회원 grade, status 수정
	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getGrade());
			pstmt.setString(2, m.getStatus());
			pstmt.setInt(3, m.getMemberNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//회원 아이디,이름으로 검색 및 검색결과 조회
	public ArrayList<Member> selectUserList2(Connection conn, String searchText,String searchField) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("findUser")+searchField.trim();
		ArrayList<Member> flist = new ArrayList<>();
		
		try {
            if(searchText != null && !searchText.equals("") ){
                sql +=" LIKE '%"+searchText.trim()+"%'";
            }
			
            pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				flist.add(new Member(rset.getInt("MEMBER_NO")
									,rset.getString("MEMBER_ID")
									,rset.getString("MEMBER_NAME")
									,rset.getString("MEMBER_NICKNAME")
									,rset.getString("MEMBER_EMAIL")
									,rset.getDate("MEMBER_ENROLL_DATE")
									,rset.getString("MEMBER_GRADE")
									,rset.getString("STATUS")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return flist;
	}

	//정지회원 조회
	
	public ArrayList<Member> selectDisableList(Connection conn) {

		ResultSet rset = null;
		Statement stmt = null;
		String sql = prop.getProperty("selectDisable");
		ArrayList<Member> dlist = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				
				dlist.add(new Member(rset.getInt("MEMBER_NO")
									,rset.getString("MEMBER_ID")
									,rset.getString("MEMBER_NAME"),
									rset.getString("MEMBER_EMAIL"),
									rset.getDate("MEMBER_ENROLL_DATE")
									,rset.getString("DISABLE_REASON")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return dlist;
	}
	
	//정지회원테이블 조회 
	public boolean selectDisable(Connection conn,int memberNo) {

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectDisableUser");
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			flag = rset.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return flag;
	}

	//정지회원 정지사유 업데이트 
	public int updateDisable(Connection conn, Member d) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDisable");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getReason());
			pstmt.setInt(2, d.getMemberNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//정지회원 테이블에 status n 인 회원 추가하기 DAO
	public int insertDisable(Connection conn,int memberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertDisable");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//정지회원 테이블 status y 들어갈 경우 delete
	public int deleteDisable(Connection conn, int memberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteDisable");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}