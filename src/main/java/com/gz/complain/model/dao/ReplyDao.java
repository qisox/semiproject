package com.gz.complain.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.complain.model.vo.Reply;
import com.gz.post.model.dao.PostDao;

public class ReplyDao {
	
	private Properties prop = new Properties();

	public ReplyDao() {

		try {
			String filePath = ComplainDao.class.getResource("/db/sql/complain-mapper.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Reply> selectReplyList(int complainNo, Connection conn) {

		ArrayList<Reply> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, complainNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				
				list.add(new Reply(rset.getInt("REPLY_NO")
									,rset.getString("MEMBER_NICKNAME")
									,rset.getString("REPLY_CONTENT")
									,rset.getDate("REPLY_DATE")));
				
			}
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		
		
		
		return list;
	}

	public int insertReply(Connection conn, Reply r) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getComplainNo());
			pstmt.setString(2, r.getReplyWriterNo());
			pstmt.setString(3, r.getReplyContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteReply(Connection conn, Reply r) {

		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getComplainNo());
			pstmt.setInt(2, r.getReplyNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			JDBCTemplate.close(pstmt);
			
		}
		
		
		return result;
	}
	
	
	

}
