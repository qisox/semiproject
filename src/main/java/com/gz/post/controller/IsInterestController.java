package com.gz.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.post.model.service.PostService;


@WebServlet("/isInterest.po")
public class IsInterestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public IsInterestController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		int result = new PostService().IsInterest(postNo,mno);
		//좋아요 테이블에 게시글번호랑 멤버번호가 일치하는 행이 있다면
		System.out.println("result : "+result);
		if(result > 0) {
			response.getWriter().print("exist");
		} else {
			response.getWriter().print("notExist");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
