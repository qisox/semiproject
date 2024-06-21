package com.gz.mypage.update.model.service;

import java.sql.Connection;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.update.model.dao.DeleteDao;

public class DeleteService {
	//관심글 삭제 메소드
	public int deleteInterest(int mno, String[] folderNoArr, String[] postNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new DeleteDao().deleteInterest(conn,mno,folderNoArr,postNoArr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}
	//좋아요 한 글 삭제 메소드
	public int deleteLike(int mno, String[] postNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new DeleteDao().deleteLike(conn,mno,postNoArr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//내가 쓴 글 삭제 메소드
	public int deleteMyPost(int mno, String[] postNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new DeleteDao().deleteLike(conn,mno,postNoArr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//내가 쓴 댓글 삭제 메소드
	public int deleteMyComment(int mno, String[] commentNoArr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new DeleteDao().deleteLike(conn,mno,commentNoArr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
