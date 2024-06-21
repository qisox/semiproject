package com.gz.post.controller.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.post.model.service.PostService;

@WebServlet("/insertToLikeTable.po")
public class InsertToLikeTableController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertToLikeTableController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	int postNo = Integer.parseInt(request.getParameter("postNo"));
    	int mno = Integer.parseInt(request.getParameter("mno"));
    	
    	int result = new PostService().insertToLikeTable(mno,postNo);
    	
    	response.setContentType("text/html; charset=UTF-8");
    	if(result > 0) {
    		response.getWriter().print("success");
    	} else {
    		response.getWriter().print("fail");
    	}
		
		
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
     

           
                
    }
}