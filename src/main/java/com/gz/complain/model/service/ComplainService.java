package com.gz.complain.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;
import com.gz.complain.model.dao.ComplainDao;
import com.gz.post.model.dao.PostDao;
import com.gz.complain.model.vo.ComAttachment;
import com.gz.complain.model.vo.Complain;

public class ComplainService {

	
	//신고/건의 게시판 전체 조회
		public ArrayList<Complain> selectComplainList(PageInfo pi) {

			Connection conn = JDBCTemplate.getConnection();
		
			ArrayList<Complain> list = new ComplainDao().selectComplainList(conn,pi);
			
			JDBCTemplate.close(conn);
			
			
			
			
			return list;
		}
		
		//신고,건의 상세조회
		
		public Complain selectComplain(int complainNo) {

			Connection conn = JDBCTemplate.getConnection();
			
			Complain c = new ComplainDao().selectComplain(conn,complainNo);
			
			JDBCTemplate.close(conn);
			
			return c;
		}
		
		//건의사항
		
		public int increaseCount2(int complainNo) {

			Connection conn = JDBCTemplate.getConnection();

			int result = new ComplainDao().increaseCount2(complainNo, conn);

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

			JDBCTemplate.close(conn);

			return result;
		}
		
		public ComAttachment selectAttachment2(int complainNo) {
			
			Connection conn = JDBCTemplate.getConnection();

			ComAttachment at = new ComplainDao().selectAttachment2(conn, complainNo);

			JDBCTemplate.close(conn);

			return at;
			
			
			
			
		}
		
		//건의사항 글작성
		
		public int insertComplain(Complain c, ComAttachment at) {

			Connection conn = JDBCTemplate.getConnection();

			int result = new ComplainDao().insertComplain(conn, c);

			int result2 = 1;

			if (at != null) {
				result2 = new ComplainDao().insertAttachment2(conn, at);
			}

			if (result * result2 > 0) {

				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

			return result * result2;
		}
		
		//건의사항 업데이트
		public int updateComplain(Complain c, ComAttachment at) {
			
			
			Connection conn = JDBCTemplate.getConnection();
			
			//게시글 정보 수정
			int result = new ComplainDao().updateComplain(conn, c);
		
			//첨부파일이 있으면 처리 후 담기 변수 준비
			//첨부파일이 없으면 게시글 처리만 하기위해 1로 초기화
			int result2 = 1;
			
			//첨부파일이 있다면 수정 또는 추가 작업 수행
			if(at!=null) {
			// 기존 첨부파일이 있다면(update) - fileNo가 있는지 확인
				if(at.getFileNo()!=0) {
					result2 = new ComplainDao().updateAttachment2(conn,at);
				} else { // 기존에 첨부파일이 없었다면 - insert
					// 기존에 첨부파일 추가 메소드에서는 sql구문이
					// refBno(참조게시글번호) 부분이 currval로 들어가있어서 사용할 수 업사
					// controller에서 가져온 boardNo를 넣어서 추가한다.
					
					result2 = new ComplainDao().insertNewAttachment2(conn, at);
					
				}
			}
			
			//게시글 수정과 첨부파일 수정 또는 추가작업중 하나라도 잘못된다면
			//되돌리기 작업을 수행해야한다
			if(result * result2 >0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			return result * result2;
			
			
			
		}
		
		
		//건의사하 ㅇ삭제
		public int deleteComplain(int complainNo) {

			Connection conn = JDBCTemplate.getConnection();
			
			int result = new ComplainDao().delectComplain(conn,complainNo);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else{
				JDBCTemplate.rollback(conn);
			}
			
			return result;
		}

		//페이징바
		public int listCount() {

			Connection conn = JDBCTemplate.getConnection();

			int count = new ComplainDao().listCount(conn);

			JDBCTemplate.close(conn);

			return count;
		}
		
}
