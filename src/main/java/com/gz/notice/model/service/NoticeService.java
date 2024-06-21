package com.gz.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;
import com.gz.notice.model.dao.NoticeDao;
import com.gz.notice.model.vo.Notice;
import com.gz.post.model.vo.Attachment;

public class NoticeService {

	//공지사항 개수 조회 메소드
	public int listCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new NoticeDao().listCount(conn);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

	//공지사항 목록 조회 메소드
	public ArrayList<Notice> selectList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> nlist = new NoticeDao().selectList(conn,pi);
		
		JDBCTemplate.close(conn);
		
		return nlist;
	}

	//조회수 증가 메소드
	public int increaseCount(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().increaseCount(noticeNo,conn);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//공지사항 상세조회
	public Notice selectNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Notice n = new NoticeDao().selectNotice(noticeNo,conn);
		
		JDBCTemplate.close(conn);
		
		return n;
	}

	//공지사항 등록
	public int insertNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().insertNotice(conn,n);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//공지사항 수정
	public int updateNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().updateNotice(conn,n);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().deleteNotice(conn,noticeNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
