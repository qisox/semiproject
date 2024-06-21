package com.gz.mypage.myComment.model.vo;

import java.sql.Date;


//댓글 vo
public class MyComment {
	private int commentNo;//댓글 고유 번호	COMMENT_NO	NUMBER
	private int postNo;//댓글 단 게시글 번호	POST_NO	NUMBER
	private String categoryName;	//카테고리 이름 ( 내가 쓴 댓글 목록 조회시 활용)
	private String writerNo;//번호를 이용해서 댓글작성자 닉네임을 담을것이기 때문에 String으로 처리. COMMENT_WRITER_NO	NUMBER
	private String content;// 댓글 내용 COMMENT_CONTENT	VARCHAR2(1000 BYTE)
	private Date commentDate;//댓글 작성일	COMMENT_DATE	DATE
	private String postTitle; //댓글 단 게시글 제목 (내가 쓴 댓글 목록조회시 활용)
	
	public MyComment() {
		super();
	}

	
	
	//내가 쓴 댓글 목록 조회시 활용될 생성자
	public MyComment(int commentNo, String categoryName, String content,String writerNo , Date commentDate,
			String postTitle) {
		super();
		this.commentNo = commentNo;
		this.categoryName = categoryName;
		this.content = content;
		this.writerNo = writerNo;
		this.commentDate = commentDate;
		this.postTitle = postTitle;
	}
	
	//일반적인 댓글 관련 기능 구현시 활용될 생성자
	public MyComment(int commentNo, int postNo, String writerNo, String content, Date commentDate) {
		super();
		this.commentNo = commentNo;
		this.postNo = postNo;
		this.writerNo = writerNo;
		this.content = content;
		this.commentDate = commentDate;
	}



	public int getCommentNo() {
		return commentNo;
	}



	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}



	public int getPostNo() {
		return postNo;
	}



	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getWriterNo() {
		return writerNo;
	}



	public void setWriterNo(String writerNo) {
		this.writerNo = writerNo;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Date getCommentDate() {
		return commentDate;
	}



	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}



	public String getPostTitle() {
		return postTitle;
	}



	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}



	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", postNo=" + postNo + ", categoryName=" + categoryName
				+ ", writerNo=" + writerNo + ", content=" + content + ", commentDate=" + commentDate + ", postTitle="
				+ postTitle + "]";
	}
	
	
	
	
	
	
	
}
