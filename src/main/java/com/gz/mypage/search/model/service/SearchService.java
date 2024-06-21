package com.gz.mypage.search.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.mypage.interest.model.vo.Interest;
import com.gz.mypage.like.model.vo.Like;
import com.gz.mypage.myComment.model.vo.MyComment;
import com.gz.mypage.myPost.model.vo.MyPost;
import com.gz.mypage.search.model.dao.SearchDao;

public class SearchService {
	
	
	//관심글 검색 메소드
	public ArrayList<Interest> searchInterest(int mno, String inputData) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Interest> list = new SearchDao().searchInterest(conn,mno,inputData);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//좋아요 검색 메소드
	public ArrayList<Like> searchLike(int mno, String inputData) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Like> list = new SearchDao().searchLike(conn,mno,inputData);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//내가 쓴 글 검색 메소드
	public ArrayList<MyPost> searchMyPost(int mno, String inputData) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MyPost> list = new SearchDao().searchMyPost(conn,mno,inputData);
		JDBCTemplate.close(conn);
		return list;
	}
	
	//내가 쓴 댓글 검색 메소드
	public ArrayList<MyComment> searchMyComment(int mno, String inputData) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<MyComment> list = new SearchDao().searchMyComment(conn,mno,inputData);
		JDBCTemplate.close(conn);
		return list;
	}

}
