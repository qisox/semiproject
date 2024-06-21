package com.gz.complain.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.admin.model.service.AdminService;
import com.gz.common.model.vo.PageInfo;
import com.gz.complain.model.service.ComplainService;
import com.gz.post.model.service.PostService;
import com.gz.complain.model.vo.Complain;


/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/complainList.bo")
public class ComplainBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplainBoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int listCount;
		int currentPage;
		int pageLimit;
		int boardLimit;
		
		int maxPage;
		int startPage;
		int endPage;
		
		listCount = new ComplainService().listCount(); //개시글 개수 조회
		String currentPageParam = request.getParameter("currentPage");
		if (currentPageParam != null && !currentPageParam.isEmpty()) {
            currentPage = Integer.parseInt(currentPageParam);
        } else {
            // currentPage가 전달되지 않은 경우에 대한 예외 처리
            currentPage = 1; // 혹은 기본적으로 보여줄 페이지 번호 설정
        }
		pageLimit = 5;
		boardLimit = 10;
		
		maxPage = (int) Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		
        
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

		ArrayList<Complain> list = new ComplainService().selectComplainList(pi);
				
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
	
			System.out.println(list+"확인용");
		request.getRequestDispatcher("views/complain/complainListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
