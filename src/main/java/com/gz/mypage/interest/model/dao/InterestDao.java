package com.gz.mypage.interest.model.dao;

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
import com.gz.mypage.interest.model.vo.Interest;

public class InterestDao {
	private Properties prop = new Properties();
	
	public InterestDao() {
		String filePath = InterestDao.class.getResource("/db/sql/mypage-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//최상단에 보일 관심글 조회(폴더에 들어가 있지 않는)
	public ArrayList<Interest> selectTopInterest(Connection conn, int mno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Interest> list = new ArrayList<>();
		String sql = prop.getProperty("selectTopInterest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Interest(rset.getInt("POST_NO")
									 ,rset.getString("TITLE")
									 ,rset.getDate("POST_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	//폴더에 존재하는 게시글 조회해오기
	public ArrayList<Interest> selectSubInterest(Connection conn,int mno, int fno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Interest> list = new ArrayList<>();
		String sql = prop.getProperty("selectSubInterest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			pstmt.setInt(2, mno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Interest(rset.getInt("POST_NO")
									 ,rset.getString("TITLE")
									 ,rset.getDate("POST_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	//뒤로가기 했을때 상위폴더에 있는 게시글을 조회해오기 위한 메소드
	public ArrayList<Interest> selectBackInterest(Connection conn, int mno, int cuFolderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Interest> list = new ArrayList<>();
		String sql = "";
		
			sql = prop.getProperty("selectBackInterest");
			try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cuFolderNo);
			pstmt.setInt(2, mno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Interest(rset.getInt("POST_NO")
									 ,rset.getString("TITLE")
									 ,rset.getDate("POST_DATE")));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		
		
		
		return list;
	}
	
	//폴더 추가하기
	public int addFolder(Connection conn,int mno, String folderName,int currentFolderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("addFolder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, folderName);
			if(currentFolderNo == 0) {
				pstmt.setString(3, null);
			} else {
				pstmt.setInt(3, currentFolderNo);
			}
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
