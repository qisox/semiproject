package com.gz.mypage.update.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.mypage.update.model.service.DeleteService;


@WebServlet("/likeDelete.my")
public class LikeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LikeDeleteController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		String[] postNoArr = request.getParameterValues("postNoArr[]");
		
		
		int result = new DeleteService().deleteLike(mno,postNoArr);
		
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
