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

import com.gz.admin.model.vo.Category;
import com.gz.common.JDBCTemplate;

public class CategoryDao {
	private Properties prop = new Properties();
	
	public CategoryDao() {
		String filePath = CategoryDao.class.getResource("/db/sql/category-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Category> selectClist(Connection conn) {
		ArrayList<Category> clist = new ArrayList<>();
		ResultSet rset = null;
		Statement stmt = null;
		
		String sql = prop.getProperty("selectClist");
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()){
				clist.add(new Category(rset.getInt("CATEGORY_NO")
									  ,rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return clist;
	}
	
	//카테고리 추가
	public int insertCategpry(Connection conn, Category c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getCategoryNo());
			pstmt.setString(2, c.getCategoryName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//카테고리 수정
	public int updateCategory(Connection conn, Category c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getCategoryName());
			pstmt.setInt(2, c.getCategoryNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//카테고리 업데이트 후 조회
	public Category selectClist2(Connection conn, int categoryNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Category c = null;
		String sql = prop.getProperty("selectClist2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				c = new Category(rset.getInt("CATEGORY_NO")
								,rset.getString("CATEGORY_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return c;
	}

	public int deleteCategory(Connection conn, String categoryName, int categoryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, categoryName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}