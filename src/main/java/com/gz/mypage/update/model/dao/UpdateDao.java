package com.gz.mypage.update.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.member.model.vo.Member;

public class UpdateDao {
	private Properties prop = new Properties();
	
	public UpdateDao() {
		String filePath = UpdateDao.class.getResource("/db/sql/mypage-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//멤버 정보 업데이트
		public int updateMember(Connection conn,String userId, String userName, String userNickName, String email) {
			PreparedStatement pstmt = null;
			int result = 0;

			String sql = prop.getProperty("updateMember");

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,userName);
				pstmt.setString(2, userNickName);
				pstmt.setString(3, email);
				pstmt.setString(4, userId);
				result = pstmt.executeUpdate();

				System.out.println("result dao : "+result);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
		
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		
		//유저 정보 가져오기
		public Member selectMember(Connection conn, String userId) {
			PreparedStatement pstmt = null;
			Member m = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectMember");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					m = new Member(rset.getInt("MEMBER_NO")
							,rset.getString("MEMBER_ID")
							,rset.getString("MEMBER_NAME")
							,rset.getString("MEMBER_NICKNAME")
							,rset.getString("MEMBER_EMAIL")
							,rset.getDate("MEMBER_ENROLL_DATE")
							,rset.getString("MEMBER_GRADE")
							,rset.getString("STATUS"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return m;
		}
		
		//유저 비밀번호 변경 메소드
		public int updatePwd(Connection conn, int mno, String pwd, String newPwd) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updatePwd");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newPwd);
				pstmt.setString(2, pwd);
				pstmt.setInt(3, mno);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
		
		//회원 탈퇴 메소드
		public int deleteMember(Connection conn, int mno, String pwd) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("deleteMember");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mno);
				pstmt.setString(2, pwd);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

}
