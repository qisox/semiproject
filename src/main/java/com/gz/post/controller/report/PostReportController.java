package com.gz.post.controller.report;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.post.model.service.PostService;


/**
 * Servlet implementation class IncreaseInterestCountServlet
 */

@WebServlet("/insertReport.po")
public class PostReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostReportController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int memberNo = Integer.parseInt(request.getParameter("mno"));
    	int postNo = Integer.parseInt(request.getParameter("postNo"));
    	String reportReason = request.getParameter("reportReason");
    	
    	int result = new PostService().insertReportTable(memberNo, postNo, reportReason);
    	
    	System.out.println("insert Report : " + result);
    	
    	response.setContentType("html/text; charset=UTF-8");
    	if (result > 0) {
    		response.getWriter().print("success");
    	} else {
    		response.getWriter().print("fail");    		
    	}
    	
	
    	
    }
}