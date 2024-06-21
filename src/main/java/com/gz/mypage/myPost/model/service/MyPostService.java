package com.gz.mypage.myPost.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.myPost.model.dao.MyPostDao;
import com.gz.mypage.myPost.model.vo.MyPost;

public class MyPostService {
	
	//내가 쓴 글 리스트 불러오는 메소드
	public ArrayList<MyPost> selectMyPostList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MyPost> list = new MyPostDao().selectMyPostList(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}

}
