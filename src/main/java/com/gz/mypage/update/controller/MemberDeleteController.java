package com.gz.mypage.update.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.mypage.update.model.service.MemberUpdateService;

//회원탈퇴 servlet
@WebServlet("/memberDelete.my")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberDeleteController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int mno = Integer.parseInt(request.getParameter("mno"));
		String pwd = request.getParameter("pwd");
		
		int result = new MemberUpdateService().deleteMember(mno,pwd);
		
		HttpSession session = request.getSession();
		if(result > 0) {
			session.setAttribute("deleteMemAlert", "회원 탈퇴가 완료되었습니다.");
			session.removeAttribute("loginMember");
			response.sendRedirect(request.getContextPath());
		}else {
			session.setAttribute("myAlertMsg", "회원탈퇴에 실패했습니다. 비밀번호를 다시 확인해주세요.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}
	}

}
