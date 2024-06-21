package com.gz.comment.model.vo;

import java.sql.Date;


//댓글 vo
public class Comment {
	private int commentNo;//댓글 고유 번호	COMMENT_NO	NUMBER
	private int postNo;//댓글 단 게시글 번호	POST_NO	NUMBER
	private String writerNo;//번호를 이용해서 댓글작성자 닉네임을 담을것이기 때문에 String으로 처리. COMMENT_WRITER_NO	NUMBER
	private String content;// 댓글 내용 COMMENT_CONTENT	VARCHAR2(1000 BYTE)
	private Date commentDate;//댓글 작성일	COMMENT_DATE	DATE

	
	public Comment() {
		super();
	}

	

	//일반적인 댓글 관련 기능 구현시 활용될 생성자
	public Comment(int commentNo, int postNo, String writerNo, String content, Date commentDate) {
		super();
		this.commentNo = commentNo;
		this.postNo = postNo;
		this.writerNo = writerNo;
		this.content = content;
		this.commentDate = commentDate;
	}
	public Comment(int commentNo, String writerNo, String content, Date commentDate) {
		super();
		this.commentNo = commentNo;
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



	
	
	
	
	
	
}
