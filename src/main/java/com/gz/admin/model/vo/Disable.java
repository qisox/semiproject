package com.gz.admin.model.vo;

public class Disable {
	
	private int memberNo;
	private String reason;
	
	public Disable() {
		super();
	}

	public Disable(int memberNo, String reason) {
		super();
		this.memberNo = memberNo;
		this.reason = reason;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Disable [memberNo=" + memberNo + ", reason=" + reason + "]";
	}
	
	
	
	
	

}
