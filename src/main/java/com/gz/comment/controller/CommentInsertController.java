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

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/insertComment.po")
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String content = request.getParameter("content");
		Member loginUser = (Member) request.getSession().getAttribute("loginMember");
		
		int writerNo = loginUser.getMemberNo(); // 댓글작성자 번호 추출
		
		Comment c = new Comment();
		c.setPostNo(postNo);
		c.setContent(content);
		c.setWriterNo(String.valueOf(writerNo));
	
		// INSERT(DML)
		int result = new PostService().insertComment(c);
		
		response.getWriter().print(result);
	}

}
