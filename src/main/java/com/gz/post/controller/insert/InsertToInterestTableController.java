package com.gz.post.controller.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.post.model.service.PostService;

/**
 * Servlet implementation class InsertLikeTableController
 */
@WebServlet("/insertToInterestTable.po")
public class InsertToInterestTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public InsertToInterestTableController() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		int result = new PostService().insertInterestTable(mno,postNo);
		System.out.println("insert Interest : "+result);
		//응답 데이터 형식 지정하기
		response.setContentType("text/html; charset=UTF-8");
		if(result > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
