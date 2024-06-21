package com.gz.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.member.model.service.MemberService;
import com.gz.member.model.vo.Member;

/**
 * Servlet implementation class userEnrollController
 */
@WebServlet("/enrollForm.me")
public class MemberEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/member/memberEnrollForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId"); // 아이디
		String memberPwd = request.getParameter("memberPwd"); // 비밀번호 
		String memberName = request.getParameter("memberName"); // 회원 이름
		String nickname = request.getParameter("nickname"); // 닉네임
		String email = request.getParameter("email");// 이메일
			
		Member m = new Member(memberId,memberPwd,memberName,nickname,email);
				
		int result = new MemberService().insertMember(m);
				
		if(result>0) {//성공 (성공시 화면)
			HttpSession session = request.getSession();

			session.setAttribute("alertMsg", "회원가입 성공");
			response.sendRedirect(request.getContextPath());
					
		}else {
			request.setAttribute("errorMsg", "회원가입 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}
}