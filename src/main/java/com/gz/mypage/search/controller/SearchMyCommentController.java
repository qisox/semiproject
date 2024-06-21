package com.gz.mypage.search.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.myComment.model.vo.MyComment;
import com.gz.mypage.search.model.service.SearchService;


@WebServlet("/searchMyComment.my")
public class SearchMyCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchMyCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputData = request.getParameter("inputData");
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		ArrayList<MyComment> list = new SearchService().searchMyComment(mno,inputData);
		
		if(list != null) {
			//응답
			response.setContentType("json/application; charset=UTF-8");
			//한번에 처리 (gson을 이용한 list 전달)
			new Gson().toJson(list,response.getWriter());
		} else {
			request.setAttribute("errorMsg", "댓글 검색 에러");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
