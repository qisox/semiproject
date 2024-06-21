package com.gz.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.comment.model.vo.Comment;
import com.gz.member.model.vo.Member;
import com.gz.post.model.service.PostService;

@WebServlet("/updateComment.po")
public class CommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentUpdateController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String content = request.getParameter("content");
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		Member loginUser = (Member) request.getSession().getAttribute("loginMember");
		
		int writerNo = loginUser.getMemberNo(); 
		
		Comment c = new Comment();
		c.setPostNo(postNo);
		c.setContent(content);
		c.setCommentNo(commentNo);
		c.setWriterNo(String.valueOf(writerNo));
	
		
		int result = new PostService().updateComment(c);
		
		response.getWriter().print(result);
	}

}
