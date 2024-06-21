package com.gz.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.admin.model.service.AdminService;
import com.gz.member.model.vo.Member;

/**
 * Servlet implementation class UserSearchController
 */
@WebServlet("/find.ad")
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String searchText = request.getParameter("searchText"); // 검색어
		String searchField = request.getParameter("searchField"); //선택한 검색 옵션
		
		ArrayList<Member> flist = new AdminService().selectUserList2(searchText,searchField);
		
		
		request.setAttribute("list", flist);
		request.getRequestDispatcher("views/admin/userListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
