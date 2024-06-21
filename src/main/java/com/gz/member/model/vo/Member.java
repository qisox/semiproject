package com.gz.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String nickname;
	private String email;
	private Date enrolldate;
	private String grade;
	private String status;
	private String reason;
	
	public Member() {
		super();
	}
	//정지회원 조회
	public Member(int memberNo, String memberId, String memberName, String email, Date enrolldate, String reason) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.email = email;
		this.enrolldate = enrolldate;
		this.reason = reason;
	}
	//관리자 메뉴 회원등급, 사용여부 update
	public Member(int memberNo, String grade, String status, String reason) {
		super();
		this.memberNo = memberNo;
		this.grade = grade;
		this.status = status;
		this.reason = reason;
	}
	public Member(int memberNo, String grade, String status) {
		super();
		this.memberNo = memberNo;
		this.grade = grade;
		this.status = status;
	}
	


	public Member(String memberId, String memberPwd, String memberName, String nickname, String email) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.nickname = nickname;
		this.email = email;
	}

	public Member(String memberId, String memberName, String email, Date enrolldate, String reason) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.email = email;
		this.enrolldate = enrolldate;
		this.reason = reason;
		
	}	

	public Member(int memberNo, String memberId, String memberPwd, String memberName, String nickname, String email,
			Date enrolldate, String grade, String status) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.nickname = nickname;
		this.email = email;
		this.enrolldate = enrolldate;
		this.grade = grade;
		this.status = status;
	}
	
	public Member(int memberNo, String reason) {
		super();
		this.memberNo = memberNo;
		this.reason = reason;
	}
	
	public Member(int memberNo, String memberId, String memberName, String nickname, String email, Date enrolldate,
			String grade, String status) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.nickname = nickname;
		this.email = email;
		this.enrolldate = enrolldate;
		this.grade = grade;
		this.status = status;
	}


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", nickname=" + nickname + ", email=" + email + ", enrolldate=" + enrolldate + ", grade="
				+ grade + ", status=" + status + "]";
	}
	
	
	
}
