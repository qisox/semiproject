package com.gz.mypage.search.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.interest.model.vo.Interest;
import com.gz.mypage.like.model.vo.Like;
import com.gz.mypage.myComment.model.vo.MyComment;
import com.gz.mypage.myPost.model.vo.MyPost;

public class SearchDao {
	private Properties prop = new Properties();
	
	public SearchDao() {
		super();
		String filePath = SearchDao.class.getResource("/db/sql/mypage-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//관심글 검색 메소드
	public ArrayList<Interest> searchInterest(Connection conn, int mno, String inputData) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Interest> list = new ArrayList<>();
		String sql = prop.getProperty("searchInterest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, '%'+inputData+'%');
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
	//좋아요 게시글 검색 메소드
	public ArrayList<Like> searchLike(Connection conn, int mno, String inputData) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Like> list = new ArrayList<>();
		String sql = prop.getProperty("searchLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, '%'+inputData+'%');
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Like(
						rset.getInt("POST_NO")
					   ,rset.getString("CATEGORY_NAME")
					   ,rset.getString("TITLE")
					   ,rset.getString("MEMBER_NICKNAME")
					   ,rset.getInt("COUNT")
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
	//내가 쓴 글 검색 메소드
	public ArrayList<MyPost> searchMyPost(Connection conn, int mno, String inputData) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyPost> list = new ArrayList<>();
		String sql = prop.getProperty("searchMyPost");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, '%'+inputData+'%');
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new MyPost(
						rset.getInt("POST_NO")
					   ,rset.getString("CATEGORY_NAME")
					   ,rset.getString("TITLE")
					   ,rset.getString("MEMBER_NICKNAME")
					   ,rset.getInt("COUNT")
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
	
	//내가 쓴 댓글 검색 메소드
	public ArrayList<MyComment> searchMyComment(Connection conn, int mno, String inputData) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyComment> list = new ArrayList<>();
		String sql = prop.getProperty("searchMyComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, '%'+inputData+'%');
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new MyComment(
						rset.getInt("COMMENT_NO")
					   ,rset.getString("CATEGORY_NAME")
					   ,rset.getString("COMMENT_CONTENT")
					   ,rset.getString("MEMBER_NICKNAME")
					   ,rset.getDate("COMMENT_DATE")
					   ,rset.getString("TITLE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

}
