package com.gz.mypage.folder.model.vo;

import java.sql.Date;

public class Folder {
	private int folderNo;//	FOLDER_NO	NUMBER
	private int postNo;//	POST_NO	NUMBER
	private int userNo;//	USER_NO	NUMBER
	private String folderName;//	FOLDER_NAME	VARCHAR2(20 BYTE)
	private int upFolderNo;//	UP_FOLDER_NO	NUMBER
	private Date createDate;
	//	DOWN_FOLDER_NO	NUMBER
	public Folder() {
		super();
	}
	public Folder(int folderNo, int postNo, int userNo, String folderName, int upFolderNo, Date createDate) {
		super();
		this.folderNo = folderNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.folderName = folderName;
		this.upFolderNo = upFolderNo;
		this.createDate = createDate;
	}
	
	public Folder(int folderNo, String folderName) {
		super();
		this.folderNo = folderNo;
		this.folderName = folderName;
	}
	public int getFolderNo() {
		return folderNo;
	}
	public void setFolderNo(int folderNo) {
		this.folderNo = folderNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public int getUpFolderNo() {
		return upFolderNo;
	}
	public void setUpFolderNo(int upFolderNo) {
		this.upFolderNo = upFolderNo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Folder [folderNo=" + folderNo + ", postNo=" + postNo + ", userNo=" + userNo + ", folderName="
				+ folderName + ", upFolderNo=" + upFolderNo + ", createDate=" + createDate + "]";
	}
	
	
	
	
}
