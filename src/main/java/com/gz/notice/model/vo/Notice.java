package com.gz.notice.model.vo;

import java.sql.Date;

public class Notice {

	private int noticeNo; //게시글 고유번호
	private String noticeContent; //공지사항 내용
	private String noticeTitle; //공지사항 제목
	private String noticeWriter; //작성자
	private Date noticeDate; //작성일
	private int count; //조회수
	private String status; //공지사항 활성화 여부
	private String nickName; //별명
	
	public Notice() {
		super();
	}
	
	public Notice(int noticeNo, String noticeContent, String noticeTitle, String noticeWriter, Date noticeDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeContent = noticeContent;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeDate = noticeDate;
	}

	

	public Notice(int noticeNo, String noticeContent, String noticeTitle, Date noticeDate, int count, String nickName) {
		super();
		this.noticeNo = noticeNo;
		this.noticeContent = noticeContent;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.count = count;
		this.nickName = nickName;
	}


	public Notice(int noticeNo, String noticeContent, String noticeTitle, String noticeWriter, Date noticeDate,
			int count, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeContent = noticeContent;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeDate = noticeDate;
		this.count = count;
		this.status = status;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}