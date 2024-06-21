package com.gz.mypage.folder.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.folder.model.dao.FolderDao;
import com.gz.mypage.folder.model.vo.Folder;
import com.gz.mypage.interest.model.vo.Interest;

public class FolderService {
	
	//최상위 폴더 조회해오기
	public ArrayList<Folder> selectFolder(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Folder> fList = new FolderDao().selectFolder(conn,mno);
		JDBCTemplate.close(conn);
		return fList;
	}
	//하위 폴더 조회해오기
	public ArrayList<Folder> selectSubFolder(int mno,int fno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Folder> list = new FolderDao().selectSubFolder(conn,mno,fno);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//뒤로가기 눌렀을때 상위 폴더로 이동하기
	public ArrayList<Folder> selectBackFolder(int mno,int cuFolderNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Folder> list = new FolderDao().selectBackFolder(conn,mno,cuFolderNo);
		JDBCTemplate.close(conn);
		return list;
	}


}
