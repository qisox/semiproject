package com.gz.mypage.myComment.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.myComment.model.service.MyCommentService;
import com.gz.mypage.myComment.model.vo.MyComment;


//마이페이지에서 접근할 controller. 내가 쓴 댓글 관련 작업
@WebServlet("/myCommentList.my")
public class MyCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyCommentListController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		ArrayList<MyComment> list = new MyCommentService().selectMyCommentList(mno);
		if(list != null) {
			//응답 
			response.setContentType("json/application; charset=UTF-8"); //한번에 처리
			 //(gson을 이용한 list 전달) 
			 new Gson().toJson(list,response.getWriter());
		} else {
			request.setAttribute("errorMsg", "폴더 에러");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
