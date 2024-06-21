package com.gz.member.model.service;

import java.sql.Connection;

import com.gz.common.JDBCTemplate;
import com.gz.member.model.dao.MemberDao;
import com.gz.member.model.vo.Member;

public class MemberService {

	// 회원가입
	public int insertMember(Member m) {
			
		Connection conn = JDBCTemplate.getConnection();
			
		int result = new MemberDao().insertMember(conn, m);
			
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
			
		JDBCTemplate.close(conn);
			
		return result;
	}
	// 로그인
	public Member loginMember(String memberId, String memberPwd) {
			
		Connection conn = JDBCTemplate.getConnection();
			
		Member m = new MemberDao().loginMember(conn, memberId, memberPwd);
			
		JDBCTemplate.close(conn);
			
		return m;
	}
	// 아이디 중복체크
	public int checkId(String checkId) {
			
		Connection conn = JDBCTemplate.getConnection();
			
		int count = new MemberDao().checkId(conn, checkId);
			
		JDBCTemplate.close(conn);
			
		return count;
	}
	// 로그인창에서 아이디 찾기 (수정 x)
	public String findId(String nickname, String email) {
		
		Connection conn = JDBCTemplate.getConnection();
			
		String memberId = new MemberDao().findId(conn, nickname, email);
			
		JDBCTemplate.close(conn);
			
		return memberId;
			
	}
	// 로그인창에서 비밀번호 찾기 (수정 x)
	public String findPwd(String memberName, String memberId, String email) {
			
		Connection conn = JDBCTemplate.getConnection();
			
		String memberPwd = new MemberDao().findPw(conn, memberName, memberId, email);
			
		JDBCTemplate.close(conn);
		
		return memberPwd;
		
	}
	//닉네임 중복 체크
	public int checkNick(String checkNick) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().checkNick(conn, checkNick);
			
		JDBCTemplate.close(conn);
		
		return count;
	
	}

}
