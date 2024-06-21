package com.gz.admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.admin.model.dao.AdminDao;
import com.gz.member.model.vo.Member;
import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;

public class AdminService {

	// 회원 개수 조회
	public int listCount() {
		Connection conn = JDBCTemplate.getConnection();

		int count = new AdminDao().listCount(conn);

		JDBCTemplate.close(conn);

		return count;
	}

	// 회원 목록 조회
	public ArrayList<Member> selectUserList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> list = new AdminDao().selectUserList(conn, pi);

		JDBCTemplate.close(conn);

		return list;
	}

	// 정지테이블 회원 조회
	public boolean selectDisable(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();

		boolean flag = new AdminDao().selectDisable(conn, memberNo);

		return flag;
	}

	public int updateMember(Member m, boolean flag) {
		Connection conn = JDBCTemplate.getConnection();

		// 회원정보 수정 (등급,상태값)
		int result1 = new AdminDao().updateMember(conn, m);

		String status = m.getStatus();

		int result2 = 1;

		if (status != null) {

			if (flag) {//정지회원이면 
				result2 = new AdminDao().deleteDisable(conn, m.getMemberNo());
			} else {//정지회원 아니면 
				result2 = new AdminDao().insertDisable(conn, m.getMemberNo());
			}
		}
		
		if(result1*result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result1*result2;

	}

	// 회원 아이디,이름으로 검색 후 조회
	public ArrayList<Member> selectUserList2(String searchText, String searchField) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> fist = new AdminDao().selectUserList2(conn, searchText, searchField);

		JDBCTemplate.close(conn);

		return fist;
	}

	// 정지회원 조회
	public ArrayList<Member> selectDisableList() {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Member> dlist = new AdminDao().selectDisableList(conn);

		JDBCTemplate.close(conn);

		return dlist;
	}

	public int updateDisable(Member d) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new AdminDao().updateDisable(conn, d);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;

	}
}