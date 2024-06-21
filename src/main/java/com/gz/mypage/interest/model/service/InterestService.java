package com.gz.mypage.interest.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.folder.model.vo.Folder;
import com.gz.mypage.interest.model.dao.InterestDao;
import com.gz.mypage.interest.model.vo.Interest;

public class InterestService {
	
	//최상단에 보일 관심글 조회(폴더에 들어가 있지 않는)
	public ArrayList<Interest> selectTopInterest(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Interest> list = new InterestDao().selectTopInterest(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}

	//폴더에 존재하는 게시글 조회해오기
	public ArrayList<Interest> selectSubInterest(int mno,int fno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Interest> list = new InterestDao().selectSubInterest(conn,mno,fno);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//뒤로가기 했을때 상위폴더에 있는 게시글을 조회해오기 위한 메소드
	public ArrayList<Interest> selectBackInterest(int mno, int cuFolderNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Interest> list = new InterestDao().selectBackInterest(conn,mno,cuFolderNo);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//폴더 추가하기
	public int addFolder(int mno, String folderName,int currentFolderNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new InterestDao().addFolder(conn, mno, folderName,currentFolderNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
