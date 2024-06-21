package com.gz.complain.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.complain.model.service.ReplyService;
import com.gz.complain.model.vo.Reply;
import com.gz.member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/insertReply.co")
public class ReplyInsertContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertContoller() {
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
		
		int complainNo = Integer.parseInt(request.getParameter("complainNo"));
		String replyContent = request.getParameter("replyContent");
		Member loginMember = (Member) request.getSession().getAttribute("loginMember");
		
		int replyWriterNo= loginMember.getMemberNo(); // 댓글작성자 번호 추출
		
		Reply r = new Reply();
		r.setComplainNo(complainNo);
		r.setReplyContent(replyContent);
		r.setReplyWriterNo(String.valueOf(replyWriterNo));
	
		// INSERT(DML)
		int result = new ReplyService().insertReply(r);
		
		response.getWriter().print(result);
	}

}
