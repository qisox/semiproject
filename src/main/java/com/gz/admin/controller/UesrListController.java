package com.gz.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.admin.model.service.AdminService;
import com.gz.common.model.vo.PageInfo;
import com.gz.member.model.vo.Member;

/**
 * Servlet implementation class UesrListController
 */
@WebServlet("/selectUser.ad")
public class UesrListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UesrListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount;
		int currentPage;
		int pageLimit;
		int selectLimit;
		
		int maxPage;
		int startPage;
		int endPage;
		
		listCount = new AdminService().listCount();
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		pageLimit = 5;
		selectLimit = 10;
		
		maxPage = (int) Math.ceil((double)listCount / selectLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, selectLimit, maxPage, startPage, endPage);
		
		ArrayList<Member> list = new AdminService().selectUserList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
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
