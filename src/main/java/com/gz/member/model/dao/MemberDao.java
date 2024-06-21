package com.gz.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.member.model.vo.Member;


public class MemberDao {

	private Properties prop = new Properties();
	
		
	public MemberDao() {
		//컴파일된 파일 기준으로 읽어올 xml파일 경로 찾기
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		
		try {
			//찾은 경로를 넣고 해당 파일 읽어오기
			prop.loadFromXML(new FileInputStream(filePath));
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 회원가입하기
	public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getNickname());
			pstmt.setString(5, m.getEmail());

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 로그인하기
	public Member loginMember(Connection conn, String memberId, String memberPwd) {
		
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		Member m = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				m = new Member(rset.getInt("MEMBER_NO")
						      ,rset.getString("MEMBER_ID")
						      ,rset.getString("MEMBER_PWD")
						      ,rset.getString("MEMBER_NAME")
						      ,rset.getString("MEMBER_NICKNAME")
						      ,rset.getString("MEMBER_EMAIL")
						      ,rset.getDate("MEMBER_ENROLL_DATE")
						      ,rset.getString("MEMBER_GRADE")
						      ,rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}	
		return m;
	}

	// 아이디 중복체크
	public int checkId(Connection conn, String checkId) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkId");
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	// 로그인 창에서 아이디 찾기
	public String findId(Connection conn, String nickname, String email) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String memberId = null;
		
		String sql = prop.getProperty("findId");
		System.out.println(nickname);
		System.out.println(email);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				memberId = rset.getString("MEMBER_ID");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("memberId : "+memberId);
		return memberId;
		
	}

	// 로그인 창에서 비밀번호찾기
	public String findPw(Connection conn, String memberName, String memberId, String email) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String memberPwd = null;
		
		String sql = prop.getProperty("findPw");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberId);
			pstmt.setString(3, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				memberPwd = rset.getString("MEMBER_PWD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
			
		return memberPwd;
	}

	public String ProfileImage(Connection conn, String memberId) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String imagePath = null;
		
		String sql = prop.getProperty("profileImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				imagePath = rset.getString("IMAGE_PATH");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return imagePath;
	}
	//닉네임 중복
	public int checkNick(Connection conn, String checkNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String sql = prop.getProperty("checkNick");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkNick);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}


}
		
		