package com.gz.complain.model.vo;

import java.sql.Date;

public class Complain {
	
	private int complainNo;						//	COMPLAIN_NO
	private String complainCategory;			//  COMPLAIN_CATEGORY
	private String complainTitle;				//	COMPLAIN_TITLE
	private String complainContent;				//	COMPLAIN_CONTENT
	private String complainWriter;				//	COMPLAIN_WRITER
	private int count;							//  COUNT
	private Date complainDate;					//	COMPLAIN_DATE
	private String status;						//	STATUS
	private Date modifiedDate;
	public Complain() {
		super();
	}

	
	
	
	
	
	
	public Complain(int complainNo, String complainCategory, String complainTitle, String complainWriter, int count,
			Date complainDate) {
		super();
		this.complainNo = complainNo;
		this.complainCategory = complainCategory;
		this.complainTitle = complainTitle;
		this.complainWriter = complainWriter;
		this.count = count;
		this.complainDate = complainDate;
	}







	public Complain(int complainNo, String complainCategory, String complainTitle, String complainContent,
			String complainWriter) {
		super();
		this.complainNo = complainNo;
		this.complainCategory = complainCategory;
		this.complainTitle = complainTitle;
		this.complainContent = complainContent;
		this.complainWriter = complainWriter;
	}







	public Complain(int complainNo, String complainCategory, String complainTitle, String complainContent,
			String complainWriter, int count, Date complainDate, String status) {
		super();
		this.complainNo = complainNo;
		this.complainCategory = complainCategory;
		this.complainTitle = complainTitle;
		this.complainContent = complainContent;
		this.complainWriter = complainWriter;
		this.count = count;
		this.complainDate = complainDate;
		this.status = status;
	}

	
	
	
	
	public Complain(int complainNo, String complainCategory, String complainTitle, String complainContent,
			String complainWriter, int count, Date complainDate, String status, Date modifiedDate) {
		super();
		this.complainNo = complainNo;
		this.complainCategory = complainCategory;
		this.complainTitle = complainTitle;
		this.complainContent = complainContent;
		this.complainWriter = complainWriter;
		this.count = count;
		this.complainDate = complainDate;
		this.status = status;
		this.modifiedDate = modifiedDate;
	}







	public Date getModifiedDate() {
		return modifiedDate;
	}







	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}







	public int getComplainNo() {
		return complainNo;
	}

	public void setComplainNo(int complainNo) {
		this.complainNo = complainNo;
	}

	public String getComplainCategory() {
		return complainCategory;
	}

	public void setComplainCategory(String complainCategory) {
		this.complainCategory = complainCategory;
	}

	public String getComplainTitle() {
		return complainTitle;
	}

	public void setComplainTitle(String complainTitle) {
		this.complainTitle = complainTitle;
	}

	public String getComplainContent() {
		return complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	public String getComplainWriter() {
		return complainWriter;
	}

	public void setComplainWriter(String complainWriter) {
		this.complainWriter = complainWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getComplainDate() {
		return complainDate;
	}

	public void setComplainDate(Date complainDate) {
		this.complainDate = complainDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}







	@Override
	public String toString() {
		return "Complain [complainNo=" + complainNo + ", complainCategory=" + complainCategory + ", complainTitle="
				+ complainTitle + ", complainContent=" + complainContent + ", complainWriter=" + complainWriter
				+ ", count=" + count + ", complainDate=" + complainDate + ", status=" + status + ", modifiedDate="
				+ modifiedDate + "]";
	}

	
	
	
	
	

}
