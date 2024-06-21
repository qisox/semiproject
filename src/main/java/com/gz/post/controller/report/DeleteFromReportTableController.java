package com.gz.post.controller.report;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.post.model.service.PostService;

/**
 * Servlet implementation class DeleteFromReportTableController
 */
@WebServlet("/deleteReport.po")
public class DeleteFromReportTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFromReportTableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("mno"));
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		int result = new PostService().deleteFromReportTable(memberNo, postNo);
		
		response.setContentType("text/html; charset=UTF-8");
		
		if (result > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");			
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
