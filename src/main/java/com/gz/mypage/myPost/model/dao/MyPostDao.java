package com.gz.mypage.myPost.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.mypage.like.model.dao.LikeDao;
import com.gz.mypage.like.model.vo.Like;
import com.gz.mypage.myPost.model.vo.MyPost;

public class MyPostDao {
private Properties prop = new Properties();
	
	public MyPostDao() {
		String filePath = MyPostDao.class.getResource("/db/sql/mypage-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//내가 쓴 글 리스트 불러오는 메소드
	public ArrayList<MyPost> selectMyPostList(Connection conn, int mno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyPost> list = new ArrayList<>();
		String sql = prop.getProperty("selectMyPostList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
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
		}
		
		return list;
	}

}
