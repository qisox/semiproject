package com.gz.mypage.update.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.mypage.update.model.service.MemberUpdateService;

/**
 * Servlet implementation class MemberPwdUpdate
 */
@WebServlet("/memberPwdUpdate.my")
public class MemberPwdUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberPwdUpdate() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int mno = Integer.parseInt(request.getParameter("mno"));
		String pwd = request.getParameter("pwd");
		String newPwd = request.getParameter("newPwd");

			
		
		int result = new MemberUpdateService().updatePwd(mno,pwd,newPwd);
		
		HttpSession session = request.getSession();
		if(result > 0) {
			
			session.setAttribute("myAlertMsg", "비밀번호 변경이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
		}else {
			session.setAttribute("myAlertMsg", "비밀번호 변경에 실패하였습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}
	}

}
