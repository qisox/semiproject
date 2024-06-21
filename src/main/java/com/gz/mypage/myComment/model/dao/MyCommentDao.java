package com.gz.mypage.myComment.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.mypage.interest.model.dao.InterestDao;
import com.gz.mypage.like.model.vo.Like;
import com.gz.mypage.myComment.model.vo.MyComment;

public class MyCommentDao {
	private Properties prop = new Properties();
	
	public MyCommentDao() {
		String filePath = MyCommentDao.class.getResource("/db/sql/mypage-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//내가 쓴 댓글 조회 메소드
	public ArrayList<MyComment> selectMyCommentList(Connection conn, int mno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyComment> list = new ArrayList<>();
		String sql = prop.getProperty("selectMyCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,mno);
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
		}
		return list;
	}

}
