package com.gz.complain.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.complain.model.dao.ReplyDao;
import com.gz.complain.model.vo.Reply;

public class ReplyService {

	public ArrayList<Reply> selectReplyList(int complainNo) {

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Reply> list = new ReplyDao().selectReplyList(complainNo,conn);
		
		JDBCTemplate.close(conn);
		
		
		
		return list;
	}

	public int insertReply(Reply r) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ReplyDao().insertReply(conn,r);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
			
			JDBCTemplate.close(conn);
		
		
		
		
		return result;
	}

	public int deleteReply(Reply r) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ReplyDao().deleteReply(conn,r);
		
		if(result>0) {
			
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
			JDBCTemplate.close(conn);
		
		
		
		return result;
	}

}
