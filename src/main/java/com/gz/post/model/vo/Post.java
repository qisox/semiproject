package com.gz.post.model.vo;
import java.sql.Date;

import com.gz.category.model.vo.Category;
import com.gz.member.model.vo.Member;

public class Post {
    
    private int postNo;
    private int memberNo;
    private int categoryNo;
    private String title;
    private String content;
    private int refBno;
    private int count;
    private String nickname;
    private Date postDate;
    private int likeCount;
    private int interestCount;
    private String status;
    private String categoryName;
    private int reportCount;
    private String reportReason;
    private Member member;
    private String category;
    private Attachment attachment;
    
    
    public Post() {
    	super();
    }
    
    public Post(int postNo, int memberNo, int categoryNo, String title, String content, int count, String nickname,
            Date postDate, int likeCount, int interestCount, String status, String categoryName, Member member
            ) {
        super();
        this.postNo = postNo;
        this.memberNo = memberNo;
        this.categoryNo = categoryNo;
        this.title = title;
        this.content = content;
        this.count = count;
        this.nickname = nickname;
        this.postDate = postDate;
        this.likeCount = likeCount;
        this.interestCount = interestCount;
        this.status = status;
        this.categoryName = categoryName;
        this.member = member;
        this.category = category;
    }
    
    public Post(int postNo, String categoryName, String title, String content, int refBno, int count, String nickname,
			Date postDate, int likeCount, int interestCount, int reportCount) {
		super();
		this.postNo = postNo;
		this.categoryName = categoryName;
		this.title = title;
		this.content = content;
		this.refBno = refBno;
		this.count = count;
		this.nickname = nickname;
		this.postDate = postDate;
		this.likeCount = likeCount;
		this.interestCount = interestCount;
		this.reportCount = reportCount;
	}

	public Post(int postNo, int memberNo, int categoryNo, String title, String content, int count, Date postDate,
            int likeCount, int interestCount, String status) {
        super();
        this.postNo = postNo;
        this.memberNo = memberNo;
        this.categoryNo = categoryNo;
        this.title = title;
        this.content = content;
        this.count = count;
        this.postDate = postDate;
        this.likeCount = likeCount;
        this.interestCount = interestCount;
        this.status = status;
    }
    
    
    public Post(int postNo, String categoryName, String title, String content, int count, String nickname, Date postDate) {
        super();
        this.postNo = postNo;
        this.categoryName = categoryName;
        this.title = title;
        this.content = content;
        this.count = count;
        this.nickname = nickname;
        this.postDate = postDate;
        this.member = new Member(); // Member 객체를 생성
    }
    
    public Post(int postNo, String categoryName, String title, String content, int refBno, int count, String nickname, Date postDate,
			int likeCount, int interestCount) {
		super();
		this.postNo = postNo;
		this.categoryName = categoryName;
		this.title = title;
		this.content = content;
		this.refBno = refBno;
		this.count = count;
		this.nickname = nickname;
		this.postDate = postDate;
		this.likeCount = likeCount;
		this.interestCount = interestCount;
		
	}
    public Post(int postNo, String categoryName, String title, String content, int count, String nickname, Date postDate,
			int interestCount) {
		super();
		this.postNo = postNo;
		this.categoryName = categoryName;
		this.title = title;
		this.content = content;
		this.count = count;
		this.nickname = nickname;
		this.postDate = postDate;
		this.interestCount = interestCount;
		
	}

    //게시글 조회
    public Post(int postNo, String categoryName, String title,  String nickname, int likeCount, int interestCount, Date postDate,int count
            ) {
        super();
        this.postNo = postNo;
        this.categoryName = categoryName;
        this.title = title;
        this.nickname = nickname;
        this.likeCount = likeCount;
        this.interestCount = interestCount;
        this.postDate = postDate;
        this.count = count;
    }
    // 게시글 상세보기
	public Post(int postNo , String categoryName, String title, String content, int refBno, int count, String nickname, Date postDate,
			int interestCount) {
		super();
		this.postNo = postNo;
		this.categoryName = categoryName;
		this.title = title;
		this.content = content;
		this.refBno = refBno;
		this.count = count;
		this.nickname = nickname;
		this.postDate = postDate;
		this.interestCount = interestCount;

	}

	public Post(int postNo, int categoryNo, String title, String content, int count, String nickname, Date postDate,
            int likeCount, int interestCount, String status, String category) {
        super();
        this.postNo = postNo;
        this.categoryNo = categoryNo;
        this.title = title;
        this.content = content;
        this.count = count;
        this.nickname = nickname;
        this.postDate = postDate;
        this.likeCount = likeCount;
        this.interestCount = interestCount;
        this.status = status;
        this.category = category;
    }
	// 게시글 검색
	
	
	public String getCategoryName() {
        return categoryName;
    }
    public Post(int postNo, int categoryNo, String title, String content, int count, String nickname, Date postDate,
			int likeCount, int interestCount, String status) {
		super();
		this.postNo = postNo;
		this.categoryNo = categoryNo;
		this.title = title;
		this.content = content;
		this.count = count;
		this.nickname = nickname;
		this.postDate = postDate;
		this.likeCount = likeCount;
		this.interestCount = interestCount;
		this.status = status;
	}
    
    //인기게시글 용
    
    public Post(int postNo, String categoryName, String title, String content, int count, int likeCount,
    		int interestCount, String nickname, Date postDate) {
    	super();
    	this.postNo = postNo;
    	this.title = title;
    	this.content = content;
    	this.count = count;
    	this.nickname = nickname;
    	this.postDate = postDate;
    	this.likeCount = likeCount;
    	this.interestCount = interestCount;
    	this.categoryName = categoryName;
    }
	
    
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

	public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }
    public int getMemberNo() {
        return memberNo;
    }
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
    public int getCategoryNo() {
        return categoryNo;
    }
    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Date getPostDate() {
        return postDate;
    }
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    public int getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
    public int getInterestCount() {
        return interestCount;
    }
    public void setInterestCount(int interestCount) {
        this.interestCount = interestCount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	
	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	@Override
    public String toString() {
        return "Post [postNo=" + postNo + ", memberNo=" + memberNo + ", categoryNo=" + categoryNo + ", title=" + title
                + ", content=" + content + ", count=" + count + ", nickname=" + nickname + ", postDate=" + postDate
                + ", likeCount=" + likeCount + ", interestCount=" + interestCount + ", status=" + status + ", member="
                + member + ", categoryName=" + categoryName + "]";
    }
}
