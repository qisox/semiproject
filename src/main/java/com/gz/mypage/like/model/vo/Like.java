package com.gz.mypage.like.model.vo;

import java.sql.Date;

public class Like {
	private int postNo; //좋아요한 게시글 번호
	private String categoryNo; //카테고리 번호 (이름으로 가져올것이기 때문에 String)
	private String title; //좋아요한 게시글 제목
	private String writerNo; //작성자 번호 (이름으로 가져올것이기 때문에 String)
	private int count;//조회수
	private Date enrollDate;//등록일
	
	public Like() {
		super();
	}

	public Like(int postNo, String categoryNo, String title, String writerNo, int count, Date enrollDate) {
		super();
		this.postNo = postNo;
		this.categoryNo = categoryNo;
		this.title = title;
		this.writerNo = writerNo;
		this.count = count;
		this.enrollDate = enrollDate;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(String writerNo) {
		this.writerNo = writerNo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Like [postNo=" + postNo + ", categoryNo=" + categoryNo + ", title=" + title + ", writerNo=" + writerNo
				+ ", count=" + count + ", enrollDate=" + enrollDate + "]";
	}
	

}
