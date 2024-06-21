package com.gz.post.controller.popular;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.common.model.vo.PageInfo;
import com.gz.post.model.service.PostService;
import com.gz.post.model.vo.Post;


@WebServlet("/popularPost.po")
public class PopularPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PopularPostController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int standard = Integer.parseInt(request.getParameter("standard")); //조회순 : 1 좋아요순 : 2 관심등록순 : 3 
		int listCount = Integer.parseInt(request.getParameter("listCount"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageLimit = Integer.parseInt(request.getParameter("pageLimit")); 
		int boardLimit = Integer.parseInt(request.getParameter("boardLimit"));

		int maxPage = Integer.parseInt(request.getParameter("maxPage"));
		int startPage =  Integer.parseInt(request.getParameter("startPage"));
		int endPage = Integer.parseInt(request.getParameter("endPage")); 


	
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

		ArrayList<Post> list = new PostService().selectPopularList(standard,pi);
				
		//응답
		response.setContentType("json/application; charset=UTF-8");
		
		//한번에 처리 (gson을 이용한 list 전달)
		new Gson().toJson(list,response.getWriter());
		
		

		

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
