package com.gz.mypage.folder.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.folder.model.vo.Folder;

public class FolderDao {
	private Properties prop = new Properties();
	
	public FolderDao() {
		String filePath = FolderDao.class.getResource("/db/sql/mypage-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//최상위 폴더 조회해오기
	public ArrayList<Folder> selectFolder(Connection conn,int mno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Folder> list = new ArrayList<>();
		String sql = prop.getProperty("selectFolder");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Folder fd = new Folder();
				fd.setFolderNo(rset.getInt("FOLDER_NO"));
				fd.setFolderName(rset.getString("FOLDER_NAME"));
				fd.setCreateDate(rset.getDate("CREATE_DATE"));
				list.add(fd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//하위 폴더 조회
	public ArrayList<Folder> selectSubFolder(Connection conn,int mno,int fno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Folder> list = new ArrayList<>();
		String sql = prop.getProperty("selectSubFolder");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setInt(2, fno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Folder fd = new Folder();
				fd.setFolderNo(rset.getInt("FOLDER_NO"));
				fd.setFolderName(rset.getString("FOLDER_NAME"));
				fd.setCreateDate(rset.getDate("CREATE_DATE"));
				list.add(fd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	//뒤로가기 눌렀을때 상위 폴더로 이동하기
	public ArrayList<Folder> selectBackFolder(Connection conn, int mno, int cuFolderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Folder> list = new ArrayList<>();
		String sql = prop.getProperty("selectBackFolder");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setInt(2, cuFolderNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Folder fd = new Folder();
				fd.setFolderNo(rset.getInt("FOLDER_NO"));
				fd.setFolderName(rset.getString("FOLDER_NAME"));
				fd.setCreateDate(rset.getDate("CREATE_DATE"));
				fd.setUpFolderNo(rset.getInt("UP_FOLDER_NO"));
				list.add(fd);
			}
			//뒤로가기 버튼을 눌렀을때 list가 비어있는 경우는 최상단에 도착했단 의미기 때문에 초기에 폴더를 설정한
			//selectFolder()메소드를 이용해서 list를 채운다.
			if(list.isEmpty()) {
				list = selectFolder(conn, mno);
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
