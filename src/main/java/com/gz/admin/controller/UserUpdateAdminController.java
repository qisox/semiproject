package com.gz.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.admin.model.service.AdminService;
import com.gz.member.model.vo.Member;

/**
 * Servlet implementation class UserUpdateAdminController
 */
@WebServlet("/update.ad")
public class UserUpdateAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateAdminController() {
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
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String grade = request.getParameter("grade");
		String status = request.getParameter("status");
		String reason = request.getParameter("reason");
		
		//정지테이블에 있는지 조회
		boolean flag = new AdminService().selectDisable(memberNo);
		
		Member m = new Member(memberNo,grade,status,reason);

		if(flag) {//정지회원일때 
			if(status.equals("N")) { //정지회원이면서 상태값 N으로 넘어올때(상태값 변경없이 넘어옴)
				status = null;
			}
		}else {//정지회원 아닐때
			if(status.equals("Y")) { //정지회원 아니면서 상태값 Y로 넘어올때(상태값 변경없이 넘어
				status = null;
			}
		}
		
		int result = new AdminService().updateMember(m,flag);
		
		HttpSession session = request.getSession();
		if(result>0) {
				session.setAttribute("alertMsg", "회원 정보 수정 성공");
				response.sendRedirect(request.getContextPath()+"/selectUser.ad?currentPage=1");
		}else {
			session.setAttribute("alertMsg", "회원 정보 수정 실패.");
			response.sendRedirect(request.getContextPath()+"/selectUser.ad?currentPage=1");
		}
	}
}