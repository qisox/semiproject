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


@WebServlet("/deleteReply.co")
public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReplyDeleteController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} 

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int complainNo = Integer.parseInt(request.getParameter("complainNo"));
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		
		Member loginUser = (Member) request.getSession().getAttribute("loginMember");
		
		int replyWriterNo = loginUser.getMemberNo(); 
		
		Reply r = new Reply();
		r.setComplainNo(complainNo);
		r.setReplyNo(replyNo);
		r.setReplyWriterNo(String.valueOf(replyWriterNo));
	
		int result = new ReplyService().deleteReply(r);
		
		response.getWriter().print(result);
	}

}
