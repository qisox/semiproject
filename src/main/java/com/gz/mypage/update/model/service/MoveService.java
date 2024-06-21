package com.gz.mypage.update.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.folder.model.vo.Folder;
import com.gz.mypage.update.model.dao.MoveDao;

public class MoveService {
	
	//폴더 리스트 가져오기
	public ArrayList<Folder> selectFolderList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Folder> list = new MoveDao().selectFolderList(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//폴더/게시글 이동
	public int moveFolder(int mno,int targetFolderNo, String[] folderNoArr, String[] postNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MoveDao().moveFolder(conn,mno,targetFolderNo,folderNoArr,postNoArr);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
