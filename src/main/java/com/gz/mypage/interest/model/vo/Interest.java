package com.gz.mypage.interest.model.vo;

import java.sql.Date;

public class Interest {
	private int postNo; //관심글 등록 당한 게시글 번호
	private String title;//관심글 등록한 사람의 번호
	private Date postDate;//게시글 게시한 날짜
	public Interest() {
		super();
	}
	public Interest(int postNo, String title, Date postDate) {
		super();
		this.postNo = postNo;
		this.title = title;
		this.postDate = postDate;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	@Override
	public String toString() {
		return "Interest [postNo=" + postNo + ", title=" + title + ", postDate=" + postDate + "]";
	}
	
	
}
