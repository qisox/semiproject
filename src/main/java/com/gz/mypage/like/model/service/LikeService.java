package com.gz.mypage.like.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.like.model.dao.LikeDao;
import com.gz.mypage.like.model.vo.Like;


public class LikeService {
	
	
	//좋아요한 글 조회해오기
	public ArrayList<Like> selectList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Like> list = new LikeDao().selectList(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}

}
