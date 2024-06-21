package com.gz.mypage.update.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.member.model.vo.Member;
import com.gz.mypage.update.model.service.MemberUpdateService;


@WebServlet("/memUpdate.my")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberUpdateController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userNickName = request.getParameter("userNickName");
		String email = request.getParameter("email");
		
		//업데이트 된 멤버
		Member m = new MemberUpdateService().updateMember(userId,userName,userNickName,email);
		HttpSession session = request.getSession();
		if(m !=  null) {
			
			session.setAttribute("loginMember",m);
			session.setAttribute("myAlertMsg", "정보 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}else {
			session.setAttribute("myAlertMsg", "정보 수정을 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}
	}

}
