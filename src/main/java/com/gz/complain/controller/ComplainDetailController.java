package com.gz.complain.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.complain.model.service.ComplainService;
import com.gz.complain.model.vo.ComAttachment;
import com.gz.complain.model.vo.Complain;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet("/complainDetail.bo")
public class ComplainDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplainDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 정보 조회 + 조회수 증가 처리
	 	int complainNo = Integer.parseInt(request.getParameter("complainNo"));

	    int result = new ComplainService().increaseCount2(complainNo);
	    
	    if(result > 0) {
	    	Complain c = new ComplainService().selectComplain(complainNo);
	    	ComAttachment at = new ComplainService().selectAttachment2(complainNo);
	    	request.setAttribute("c", c);
	    	request.setAttribute("at", at);//첨부파일정보도 전달
	    	request.getRequestDispatcher("views/complain/complainDetailView.jsp").forward(request, response);
	    }else { //실패
			request.getSession().setAttribute("alertMsg", "게시글 조회 실패");
			response.sendRedirect(request.getHeader("referer"));//이전 주소
		}
	    
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}