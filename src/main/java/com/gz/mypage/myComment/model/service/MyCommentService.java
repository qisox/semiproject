package com.gz.mypage.myComment.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.comment.model.vo.Comment;
import com.gz.common.JDBCTemplate;
import com.gz.mypage.myComment.model.dao.MyCommentDao;
import com.gz.mypage.myComment.model.vo.MyComment;

public class MyCommentService {
	
	//내가 쓴 댓글 조회 메소드
	public ArrayList<MyComment> selectMyCommentList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MyComment> list = new MyCommentDao().selectMyCommentList(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}

}
