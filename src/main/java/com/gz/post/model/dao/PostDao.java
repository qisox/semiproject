package com.gz.post.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.category.model.vo.Category;
import com.gz.comment.model.vo.Comment;
import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;
import com.gz.member.model.vo.Member;
import com.gz.post.model.vo.Attachment;
import com.gz.post.model.vo.Post;

public class PostDao {

	private Properties prop = new Properties();

	public PostDao() {

		try {
			String filePath = PostDao.class.getResource("/db/sql/post-mapper.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 게시글 개수 조회 메소드
	public int listCount(Connection conn) {

		int count = 0;
		ResultSet rset = null;
		Statement stmt = null;

		String sql = prop.getProperty("listCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				// 조회된 게시글 개수
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return count;

	}

	// 자유 게시글 개수 조회
	public int listFreeCount(Connection conn) {
		int count = 0;
		ResultSet rset = null;
		Statement stmt = null;

		String sql = prop.getProperty("listFreeCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				// 조회된 게시글 개수
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return count;
	}

	// 추천 게시글 개수 조회
	public int listRecommendCount(Connection conn) {
		int count = 0;
		ResultSet rset = null;
		Statement stmt = null;

		String sql = prop.getProperty("listRecommendCount");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				// 조회된 게시글 개수
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return count;
	}

	// 카테고리 별 게시글 개수 조회
	public int listCategoryCount(Connection conn, int categoryNo) {
		int count = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("listCategoryCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				// 조회된 게시글 개수
				count = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	// 게시글 목록 조회메소드
	public ArrayList<Post> selectList(Connection conn, PageInfo pi) {

		ArrayList<Post> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectList");

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Post post = new Post(rset.getInt("POST_NO"), rset.getString("CATEGORY_NAME"), rset.getString("TITLE"),
						rset.getString("CONTENT"), rset.getInt("COUNT"), rset.getInt("LIKE_COUNT"),
						rset.getInt("INTEREST_COUNT"), rset.getString("MEMBER_NICKNAME"), rset.getDate("POST_DATE"));

				// Member 정보를 가져와서 post 객체에 설정
				Member member = new Member();
				// 여기에서 Member 정보를 ResultSet로부터 읽어와서 member 객체에 설정하는 코드를 작성

				// post 객체에 member 정보 설정
				post.setMember(member);

				// 리스트에 post 객체 추가
				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	// 자유 게시글 조회
	public ArrayList<Post> selectFreeList(Connection conn, PageInfo pi) {
		ArrayList<Post> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectFreeList");

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Post post = new Post(rset.getInt("POST_NO"), rset.getString("CATEGORY_NAME"), rset.getString("TITLE"),
						rset.getString("CONTENT"), rset.getInt("COUNT"), rset.getInt("LIKE_COUNT"),
						rset.getInt("INTEREST_COUNT"), rset.getString("MEMBER_NICKNAME"), rset.getDate("POST_DATE"));

				// Member 정보를 가져와서 post 객체에 설정
				Member member = new Member();
				// 여기에서 Member 정보를 ResultSet로부터 읽어와서 member 객체에 설정하는 코드를 작성

				// post 객체에 member 정보 설정
				post.setMember(member);

				// 리스트에 post 객체 추가
				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	// 추천 게시글 조회
	public ArrayList<Post> selectRecommendList(Connection conn, PageInfo pi) {
		ArrayList<Post> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectRecommendList");

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Post post = new Post(rset.getInt("POST_NO"), rset.getString("CATEGORY_NAME"), rset.getString("TITLE"),
						rset.getString("CONTENT"), rset.getInt("COUNT"), rset.getInt("LIKE_COUNT"),
						rset.getInt("INTEREST_COUNT"), rset.getString("MEMBER_NICKNAME"), rset.getDate("POST_DATE"));

				// Member 정보를 가져와서 post 객체에 설정
				Member member = new Member();
				// 여기에서 Member 정보를 ResultSet로부터 읽어와서 member 객체에 설정하는 코드를 작성

				// post 객체에 member 정보 설정
				post.setMember(member);

				// 리스트에 post 객체 추가
				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	// 조회수 증가메소드
	public int increaseCount(int postNo, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, postNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public Post selectPost(Connection conn, int postNo) {

		Post p = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectPost");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				p = new Post(rset.getInt("POST_NO")
						, rset.getString("CATEGORY_NAME")
						, rset.getString("TITLE")
						,rset.getString("CONTENT")
						, rset.getInt("REF_BNO")
						, rset.getInt("COUNT")
						,rset.getString("MEMBER_NICKNAME")
						, rset.getDate("POST_DATE")
						, rset.getInt("LIKE_COUNT")
						,rset.getInt("INTEREST_COUNT")
						,rset.getInt("REPORT_COUNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return p;
	}

	public int updatePost(Connection conn, Post p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePost");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getTitle());
			pstmt.setString(2, p.getContent());
			pstmt.setInt(3, Integer.parseInt(p.getCategory()));
			pstmt.setInt(4, p.getPostNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 첨부파일 수정 메소드
	public int updateAttachment(Connection conn, Attachment at) {
		// DML (UPDATE)
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Category> selectCategoryList(Connection conn) {

		ArrayList<Category> list = new ArrayList<Category>();
		Statement stmt = null;
		String sql = prop.getProperty("selectCategoryList");
		ResultSet rset = null; // select
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while (rset.next()) { // 접근할 행이 있다
				list.add(new Category(rset.getInt("CATEGORY_NO"), rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public int deletePost(Connection conn, int postNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deletePost");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 댓글 작성 메소드
	public int insertComment(Connection conn, Comment c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, c.getPostNo());
			pstmt.setString(2, c.getWriterNo());
			pstmt.setString(3, c.getContent());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 댓글 목록 조회 메소드
	public ArrayList<Comment> selectCommentList(Connection conn, int postNo) {

		ResultSet rset = null;
		ArrayList<Comment> clist = new ArrayList<>();
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCommentList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				clist.add(new Comment(rset.getInt("COMMENT_NO"), rset.getString("MEMBER_NICKNAME"),
						rset.getString("COMMENT_CONTENT"), rset.getDate("COMMENT_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return clist;
	}

	// 댓글 수정 메소드
	public int updateComment(Connection conn, Comment c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateComment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, c.getContent());
			pstmt.setInt(2, c.getPostNo());
			pstmt.setInt(3, c.getCommentNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 댓글 삭제 메소드
	public int deleteComment(Connection conn, Comment c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, c.getPostNo());
			pstmt.setInt(2, c.getCommentNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 게시글 등록 메소드
	public int insertPost(Connection conn, Post p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPost");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getMemberNo());
			pstmt.setInt(2, p.getCategoryNo());
			pstmt.setString(3, p.getTitle());
			pstmt.setString(4, p.getContent());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 첨부파일 등록 메소드
	public int insertAttachment(Connection conn, ArrayList<Attachment> list) {
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			for (Attachment at : list) {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());

				// 실행후 받은 결과가 하나라도 0이 나오면 결과값을 0으로 만들기
				result *= pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	// 첨부파일 등록 메소드
		public int insertAttachment(Connection conn, Attachment at) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertAttachment");
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());

				// 실행후 받은 결과가 하나라도 0이 나오면 결과값을 0으로 만들기
				result = pstmt.executeUpdate();
				

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

	// 첨부파일 조회 메소드
	public ArrayList<Attachment> selectAttachment(Connection conn, int boardNo) {
		// SELECT 조회
		ResultSet rset = null; // 결과받을 객체 변수
		PreparedStatement pstmt = null;// 위치홀더를 이용해야하니 preparedstatement 이용
		// 첨부파일 정보 담을 객체 변수 준비 (아직 있을지 없을지 모르니 null로 초기화)
		ArrayList<Attachment> list = new ArrayList<Attachment>();
		String sql = prop.getProperty("selectAttachment");

		try {
			// 미완성된 SQL구문을 전달하며 PSTMT 객체 생성
			pstmt = conn.prepareStatement(sql);
			// 미완성된 부분(위치홀더) 값 채워주기
			pstmt.setInt(1, boardNo);

			// sql구문 완성했으니 실행 및 처리결과 받기
			rset = pstmt.executeQuery();
			// 전달받은 처리결과(행집합)이 있다면
			while (rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				list.add(at);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	// 기존게시글에 첨부파일 추가메소드
	public int insertNewAttachment(Connection conn, Attachment at) {
		// DML ( INSERT )
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 좋아요 카운트 업데이트 메소드
	public int increaseLikeCount(int postNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE POST SET LIKE_COUNT = LIKE_COUNT + 1 WHERE POST_NO = ? AND STATUS='Y'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 관심 카운트 업데이트 메소드
	public int increaseInterestCount(int postNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE POST SET INTEREST_COUNT = INTEREST_COUNT + 1 WHERE POST_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 검색 메소드
	public ArrayList<Post> selectPost(Connection conn, String searchCondition, String searchValue, String category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Post> list = new ArrayList<Post>();
		String sql = "";
		if (category.equals("꿀팁")) {

			if (searchCondition.equals("title")) {
				sql = prop.getProperty("selectTitlePost");
			} else if (searchCondition.equals("content")) {
				sql = prop.getProperty("selectContentPost");
			} else if (searchCondition.equals("nickname")) {
				sql = prop.getProperty("selectNicknamePost");
			} else if (searchCondition.equals("titlecontent")) {
				sql = prop.getProperty("selectTitleContentPost");
			}

			sql += "AND C.CATEGORY_NAME NOT IN ('자유','추천')";
		} else if (category.equals("자유")) {
			if (searchCondition.equals("title")) {
				sql = prop.getProperty("selectTitlePost");
			} else if (searchCondition.equals("content")) {
				sql = prop.getProperty("selectContentPost");
			} else if (searchCondition.equals("nickname")) {
				sql = prop.getProperty("selectNicknamePost");
			} else if (searchCondition.equals("titlecontent")) {
				sql = prop.getProperty("selectTitleContentPost");
			}

			sql += "AND C.CATEGORY_NAME = '자유'";
		} else if (category.equals("추천")) {
			if (searchCondition.equals("title")) {
				sql = prop.getProperty("selectTitlePost");
			} else if (searchCondition.equals("content")) {
				sql = prop.getProperty("selectContentPost");
			} else if (searchCondition.equals("nickname")) {
				sql = prop.getProperty("selectNicknamePost");
			} else if (searchCondition.equals("titlecontent")) {
				sql = prop.getProperty("selectTitleContentPost");
			}
			sql += "AND C.CATEGORY_NAME = '추천'";
		} else {
			// 인기글
			if (searchCondition.equals("title")) {
				sql = prop.getProperty("selectTitlePost");
			} else if (searchCondition.equals("content")) {
				sql = prop.getProperty("selectContentPost");
			} else if (searchCondition.equals("nickname")) {
				sql = prop.getProperty("selectNicknamePost");
			} else if (searchCondition.equals("titlecontent")) {
				sql = prop.getProperty("selectTitleContentPost");
			}
		}

		try {
			pstmt = conn.prepareStatement(sql);
			if (searchCondition.equals("title") || searchCondition.equals("content")
					|| searchCondition.equals("nickname")) {
				pstmt.setString(1, "%" + searchValue + "%");
			} else if (searchCondition.equals("titlecontent")) {
				pstmt.setString(1, "%" + searchValue + "%");
				pstmt.setString(2, "%" + searchValue + "%");
			}

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Post(rset.getInt("POST_NO"), rset.getString("CATEGORY_NAME"), rset.getString("TITLE"),
						rset.getString("CONTENT"), rset.getInt("COUNT"), rset.getString("MEMBER_NICKNAME"),
						rset.getDate("POST_DATE")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	// 인기글 가져오기
	public ArrayList<Post> selectPopularList(Connection conn, int standard, PageInfo pi) {
		ArrayList<Post> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = "";

		// standard 조회순 : 1 좋아요순 : 2 관심등록순 : 3
		String std = "COUNT";
		if (standard == 1) {
			sql = prop.getProperty("selectPopularListByCount");
		} else if (standard == 2) {
			sql = prop.getProperty("selectPopularListByLikeCount");
		} else {
			sql = prop.getProperty("selectPopularListByInterCount");
		}

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		System.out.println("startRow" + startRow);
		System.out.println("endRow" + endRow);
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Post post = new Post(rset.getInt("POST_NO"), rset.getString("CATEGORY_NAME"), rset.getString("TITLE"),
						rset.getString("CONTENT"), rset.getInt("COUNT"), rset.getInt("LIKE_COUNT"),
						rset.getInt("INTEREST_COUNT"), rset.getString("MEMBER_NICKNAME"), rset.getDate("POST_DATE"));
				// 리스트에 post 객체 추가
				list.add(post);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;

	}

	// db LIKE_TABLE에 유저 번호랑 게시글 번호 넣기
	public int insertLikeTable(Connection conn, int mno, int postNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertLikeTable");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, mno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 게시물 상세정보에 들어갔을 때 내가 좋아요를 한 게시물인지 아닌지 확인
	public int isLike(Connection conn, int postNo, int mno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("isLike");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setInt(2, postNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 좋아요 테이블에 넣기
	public int insertToLikeTable(Connection conn, int mno, int postNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertLikeTable");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, mno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 좋아요 테이블에서 삭제
	public int deleteFromLikeTable(Connection conn, int mno, int postNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFromLikeTable");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, mno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 좋아요 카운트 내리기
	public int decreaseLikeCount(int postNo, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("decreaseLikeCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 게시물 상세정보에 들어갔을 때 내가 관심표시를 한 게시물인지 아닌지 확인
	public int IsInterest(Connection conn, int postNo, int mno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("isInterest");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setInt(2, postNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 관심 테이블에 넣기
	public int insertToInterestTable(Connection conn, int mno, int postNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertInterestTable");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, mno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 관심 테이블에서 삭제
	public int deleteFromInterestTable(Connection conn, int mno, int postNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteFromInterestTable");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, mno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 관심 카운트 내리기
	public int decreaseInterestCount(int postNo, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("decreaseInterestCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 카테고리 리스트 가져오기
	public ArrayList<Category> getCategoryList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Category> category = new ArrayList<>();
		String sql = prop.getProperty("getCategoryList");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				category.add(new Category(rset.getInt("CATEGORY_NO"), rset.getString("CATEGORY_NAME")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return category;
	}

	// Attachment 테이블 내 REF_BNO 가져오기(게시글 리스트중에 Attachment테이블 내 REF_BNO와 게시물 번호가
	// 일치한다면
	// 사진게시물이 있는 게시글이란 뜻이기 때문에 사진 아이콘을 붙여주기 위해'
	public ArrayList<Integer> getAttachmentRefBno(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = prop.getProperty("getAttachmentRefBno");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			while (rset.next()) {
				list.add(rset.getInt("REF_BNO"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	// 꿀팁 내 카테고리 별 리스트 가져오기
	public ArrayList<Post> selectCategoryList(Connection conn, PageInfo pi, int categoryNo) {
		ArrayList<Post> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectGulCategoryList");

		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Post post = new Post(rset.getInt("POST_NO"), rset.getString("CATEGORY_NAME"), rset.getString("TITLE"),
						rset.getString("CONTENT"), rset.getInt("COUNT"), rset.getInt("LIKE_COUNT"),
						rset.getInt("INTEREST_COUNT"), rset.getString("MEMBER_NICKNAME"), rset.getDate("POST_DATE"));

				// 리스트에 post 객체 추가
				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}
	
public int insertReportTable(Connection conn, int memberNo, int postNo, String reportReason) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReportTable");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, postNo);
			pstmt.setString(3, reportReason);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int reportIslike(Connection conn, int memberNo, int postNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("reportIslike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, postNo);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteFromReportTable(Connection conn, int memberNo, int postNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteFromReportTable");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, postNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int decreaseReportCount(Connection conn, int postNo) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("decreaseReportCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	public int increaseReportCount(Connection conn, int postNo) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("increaseReportCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	

}
