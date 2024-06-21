package com.gz.mypage.update.model.service;

import java.sql.Connection;

import com.gz.common.JDBCTemplate;
import com.gz.member.model.vo.Member;
import com.gz.mypage.update.model.dao.MemberUpdateDao;

public class UpdateService {
	//내정보 업데이트
		public Member updateMember(String userId, String userName, String userNickName, String email) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new MemberUpdateDao().updateMember(conn,userId,userName,userNickName,email);
			Member m = null;
			if(result > 0) {
				JDBCTemplate.commit(conn);
				m = new MemberUpdateDao().selectMember(conn,userId);
				
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return m;
		}
		
		//내 비밀번호 변경
		public int updatePwd(int mno, String pwd, String newPwd) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new MemberUpdateDao().updatePwd(conn,mno,pwd,newPwd);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		//회원 탈퇴
		public int deleteMember(int mno, String pwd) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new MemberUpdateDao().deleteMember(conn,mno,pwd);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
}
