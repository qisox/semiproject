package com.gz.complain.model.vo;

import java.sql.Date;

public class Reply {
	
	private int replyNo;
	private int complainNo;
	private String replyWriterNo; // 작성자 닉네임을 담아야해서 string처리
	private String replyContent;
	private Date replyDate;
	private String status;
	public Reply() {
		super();
	}
	
	
	
	
	
	public Reply(int replyNo, String replyWriterNo, String replyContent, Date replyDate) {
		super();
		this.replyNo = replyNo;
		this.replyWriterNo = replyWriterNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}





	public Reply(int replyNo, int complainNo, String replyWriterNo, String replyContent, Date replyDate, String status) {
		super();
		this.replyNo = replyNo;
		this.complainNo = complainNo;
		this.replyWriterNo = replyWriterNo;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.status = status;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getComplainNo() {
		return complainNo;
	}
	public void setComplainNo(int complainNo) {
		this.complainNo = complainNo;
	}
	public String getReplyWriterNo() {
		return replyWriterNo;
	}
	public void setReplyWriterNo(String replyWriterNo) {
		this.replyWriterNo = replyWriterNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent=replyContent;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", complainNo=" + complainNo + ", replyWriterNo=" + replyWriterNo
				+ ", replyContent=" + replyContent + ", replyDate=" + replyDate + ", status=" + status + "]";
	}
	
	
	
	

}
