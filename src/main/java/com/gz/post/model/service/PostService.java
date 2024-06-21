package com.gz.post.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.gz.category.model.vo.Category;
import com.gz.comment.model.vo.Comment;
import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;
import com.gz.post.model.dao.PostDao;
import com.gz.post.model.vo.Attachment;
import com.gz.post.model.vo.Post;

public class PostService {
	private PostDao postDao = new PostDao();

	// 꿀팁 게시글 카운트 가져오기
	public int listCount() {

		Connection conn = JDBCTemplate.getConnection();

		int count = new PostDao().listCount(conn);

		JDBCTemplate.close(conn);

		return count;
	}

	// 자유 게시글 카운트 가져오기
	public int listFreeCount() {
		Connection conn = JDBCTemplate.getConnection();

		int count = new PostDao().listFreeCount(conn);

		JDBCTemplate.close(conn);

		return count;
	}

	// 추천 게시글 카운트 가져오기
	public int listRecommendCount() {
		Connection conn = JDBCTemplate.getConnection();

		int count = new PostDao().listRecommendCount(conn);

		JDBCTemplate.close(conn);

		return count;
	}

	// 카테고리 별 게시글 카운트 가져오기
	public int listCategoryCount(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();

		int count = new PostDao().listCategoryCount(conn, categoryNo);

		JDBCTemplate.close(conn);

		return count;
	}

	// 꿀팁 게시글 목록 조회메소드
	public ArrayList<Post> selectList(PageInfo pi) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Post> list = new PostDao().selectList(conn, pi);
		JDBCTemplate.close(conn);

		return list;
	}

	// 자유 게시글 목록 조회메소드
	public ArrayList<Post> selectFreeList(PageInfo pi) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Post> list = new PostDao().selectFreeList(conn, pi);
		JDBCTemplate.close(conn);

		return list;
	}

	// 추천 게시글 목록 조회메소드
	public ArrayList<Post> selectRecommendList(PageInfo pi) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Post> list = new PostDao().selectRecommendList(conn, pi);
		JDBCTemplate.close(conn);

		return list;
	}

	// 조회수 증가 메소드
	public int increaseCount(int postNo) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().increaseCount(postNo, conn);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	// 게시글 상세조회
	public Post selectPost(int postNo) {
		Connection conn = JDBCTemplate.getConnection();

		Post p = new PostDao().selectPost(conn, postNo);

		JDBCTemplate.close(conn);

		return p;
	}

	// 게시글 수정메소드 (flag가 1이면 기존 post 업데이트, 2라면 새로 추가)
	public int updatePost(Post p, ArrayList<Attachment> list,int flag) {
		Connection conn = JDBCTemplate.getConnection();

		// 게시글 정보 수정
		int result = new PostDao().updatePost(conn, p);
		
		// 첨부파일이 있으면 처리 후 담기 변수 준비
		// 첨부파일이 없으면 게시글 처리만 하기위해 1로 초기화
		int result2 = 1;
		System.out.println("listlist : "+list);
		// 첨부파일이 있다면 수정 또는 추가 작업 수행
		for(int i=0;i<list.size();i++) {
			
			// 기존 첨부파일이 있다면(update) - fileNo가 있는지 확인
			if (flag == 1) {
				result2 = new PostDao().updateAttachment(conn, list.get(i));
			} else { // 기존에 첨부파일이 없었다면 - insert
				// 기존에 첨부파일 추가 메소드에서는 sql구문이
				// refBno(참조게시글번호) 부분이 currval로 들어가있어서 사용할 수 업사
				// controller에서 가져온 boardNo를 넣어서 추가한다.

				result2 = new PostDao().insertNewAttachment(conn, list.get(i));

			}
			
		}
		

		// 게시글 수정과 첨부파일 수정 또는 추가작업중 하나라도 잘못된다면
		// 되돌리기 작업을 수행해야한다
		if (result * result2 > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result * result2;
	}

	// 카테고리 목록 조회 메소드
	public ArrayList<Category> selectCategoryList() {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Category> list = new PostDao().selectCategoryList(conn);

		JDBCTemplate.close(conn);

		return list;
	}

	public int deletePost(int postNo) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().deletePost(conn, postNo);

		// dml 트랜잭션
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	// 댓글
	public int insertComment(Comment c) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().insertComment(conn, c);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// 댓글 목록 조회 메소드
	public ArrayList<Comment> selectCommentList(int postNo) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Comment> clist = new PostDao().selectCommentList(conn, postNo);

		JDBCTemplate.close(conn);

		return clist;
	}

	// 댓글 수정 메소드
	public int updateComment(Comment c) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().updateComment(conn, c);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// 댓글 삭제 메소드
	public int deleteComment(Comment c) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().deleteComment(conn, c);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// 게시글 등록 메소드
	public int insertPost(Post p, ArrayList<Attachment> list) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().insertPost(conn, p);

		int result2 = new PostDao().insertAttachment(conn, list);

		if (result * result2 > 0) {

			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		return result * result2;
	}

	// 첨부파일 조회 메소드
	public ArrayList<Attachment> selectAttachment(int postNo) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Attachment> list = new PostDao().selectAttachment(conn, postNo);

		JDBCTemplate.close(conn);

		return list;
	}

	// 좋아요 업데이트 메소드
	public String increaseLikeCount(int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().increaseLikeCount(postNo, conn);

		if (result > 0) {
			JDBCTemplate.commit(conn);
			return "success";
		} else {
			JDBCTemplate.rollback(conn);
			return "fail";
		}
	}

	// 관심 업데이트 메소드
	public String increaseInterestCount(int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().increaseInterestCount(postNo, conn);

		if (result > 0) {
			JDBCTemplate.commit(conn);
			return "success";
		} else {
			JDBCTemplate.rollback(conn);
			return "fail";
		}
	}

	// 게시글 검색 메소드
	public ArrayList<Post> selectPost(String searchCondition, String searchValue, String category) {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Post> list = new PostDao().selectPost(conn, searchCondition, searchValue, category);

		JDBCTemplate.close(conn);

		return list;
	}

	// 인기글 가져오기
	public ArrayList<Post> selectPopularList(int standard, PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Post> list = new PostDao().selectPopularList(conn, standard, pi);

		JDBCTemplate.close(conn);

		return list;
	}

	// db LIKE_TABLE에 유저 번호랑 게시글 번호 넣기
	public int insertLikeTable(int mno, int postNo) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new PostDao().insertLikeTable(conn, mno, postNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	// 게시물 상세정보에 들어갔을 때 내가 좋아요를 한 게시물인지 아닌지 확인
	public int isLike(int postNo, int mno) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().isLike(conn, postNo, mno);
		JDBCTemplate.close(conn);
		return result;
	}

	// 좋아요 테이블에 넣기
	public int insertToLikeTable(int mno, int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().insertToLikeTable(conn, mno, postNo);
		int result2 = 0;
		if (result > 0) {
			// 좋아요 테이블에 넣는걸 성공했다면, post의 like count 플러스
			result2 = new PostDao().increaseLikeCount(postNo, conn);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result * result2;
	}

	// 좋아요 테이블에서 삭제하기
	public int deleteFromLikeTable(int mno, int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().deleteFromLikeTable(conn, mno, postNo);
		int result2 = 0;
		if (result > 0) {
			// 좋아요 테이블에서 삭제하는걸 성공했다면, post의 like count 플러스
			result2 = new PostDao().decreaseLikeCount(postNo, conn);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result * result2;
	}

	// 게시물 상세정보에 들어갔을 때 내가 관심표시를 한 게시물인지 아닌지 확인
	public int IsInterest(int postNo, int mno) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().IsInterest(conn, postNo, mno);
		JDBCTemplate.close(conn);
		return result;
	}

	// 관심 테이블에 넣기
	public int insertInterestTable(int mno, int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().insertToInterestTable(conn, mno, postNo);
		int result2 = 0;
		if (result > 0) {
			// 좋아요 테이블에 넣는걸 성공했다면, post의 like count 플러스
			result2 = new PostDao().increaseInterestCount(postNo, conn);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result * result2;
	}

	// 좋아요 테이블에서 삭제하기
	public int deleteFromInterestTable(int mno, int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PostDao().deleteFromInterestTable(conn, mno, postNo);
		int result2 = 0;
		if (result > 0) {
			// 좋아요 테이블에서 삭제하는걸 성공했다면, post의 like count 플러스
			result2 = new PostDao().decreaseInterestCount(postNo, conn);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result * result2;
	}

	// 카테고리 리스트 가져오기
	public ArrayList<Category> getCategoryList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Category> category = new PostDao().getCategoryList(conn);
		JDBCTemplate.close(conn);
		return category;
	}

	// Attachment 테이블 내 REF_BNO 가져오기(게시글 리스트중에 Attachment테이블 내 REF_BNO와 게시물 번호가
	// 일치한다면
	// 사진게시물이 있는 게시글이란 뜻이기 때문에 사진 아이콘을 붙여주기 위해'
	public ArrayList<Integer> getAttachmentRefBno() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Integer> list = new PostDao().getAttachmentRefBno(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	// 꿀팁 내 카테고리 별 리스트 가져오기
	public ArrayList<Post> selectCategoryList(PageInfo pi, int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Post> list = new PostDao().selectCategoryList(conn, pi, categoryNo);
		JDBCTemplate.close(conn);

		return list;
	}
	


	public int insertReportTable(int memberNo, int postNo, String reportReason) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PostDao().insertReportTable(conn, memberNo, postNo, reportReason);
		
		int result2 = 0;
		
		if (result > 0) {
			result2 = new PostDao().increaseReportCount(conn, postNo);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result * result2;
	}


	public int reportIslike(int memberNo, int postNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PostDao().reportIslike(conn, memberNo, postNo);
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int deleteFromReportTable(int memberNo, int postNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PostDao().deleteFromReportTable(conn, memberNo, postNo);
		
		int result2 = 0;
		
		if (result > 0) {
			result2 = new PostDao().decreaseReportCount(conn, postNo);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
	
		return result * result2;
	}

}
