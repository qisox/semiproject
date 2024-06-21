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
 * Servlet implementation class DisableInsertController
 */
@WebServlet("/updateDisable.ad")
public class DisableUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisableUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		//정지회원 정지사유 추가 및 업데이트 후 조회 
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String reason = request.getParameter("reason");//정지사유

		
		Member d = new Member(memberNo,reason);
		
		int result = new AdminService().updateDisable(d);
		
		HttpSession session = request.getSession(); 
		
		response.getWriter().write("성공");		
		
		
		if(result>0) {
			session.setAttribute("alertMsg", "회원 정지사유 등록 완료");
			response.sendRedirect(request.getContextPath()+"/disable.ad");
		}else {
			session.setAttribute("alertMsg", "회원 정지사유 등록 실패");
			response.sendRedirect(request.getContextPath()+"/disable.ad");
		}
	}
}