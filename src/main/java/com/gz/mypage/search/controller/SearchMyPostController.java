package com.gz.mypage.search.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.myPost.model.vo.MyPost;
import com.gz.mypage.search.model.service.SearchService;


@WebServlet("/searchMyPost.my")
public class SearchMyPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchMyPostController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputData = request.getParameter("inputData");
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		ArrayList<MyPost> list = new SearchService().searchMyPost(mno,inputData);
		
		if(list != null) {
			//응답
			response.setContentType("json/application; charset=UTF-8");
			//한번에 처리 (gson을 이용한 list 전달)
			new Gson().toJson(list,response.getWriter());
		} else {
			request.setAttribute("errorMsg", "게시글 검색 에러");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
