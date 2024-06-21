package com.gz.post.controller.popular;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.common.model.vo.PageInfo;
import com.gz.post.model.service.PostService;


@WebServlet("/getPageInfo.po")
public class GetPageInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetPageInfoController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount;
		int currentPage; 
		int pageLimit; 
		int boardLimit; 

		int maxPage; 
		int startPage; 
		int endPage ; 

		listCount = new PostService().listCount(); // 게시글 개수 조회해오기

		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	
		pageLimit = 10;
		boardLimit = 20;
		
		System.out.println(listCount / boardLimit);
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		System.out.println(maxPage);
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		endPage = startPage + pageLimit - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}
		System.out.println(maxPage);
		System.out.println("listCount"+listCount);
		System.out.println("currentPage"+currentPage);
		System.out.println("pageLimit"+pageLimit);
		System.out.println("boardLimit"+boardLimit);
		System.out.println("maxPage"+maxPage);
		System.out.println("startPage"+startPage);
		System.out.println("endPage"+endPage);
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

		//응답
		response.setContentType("json/application; charset=UTF-8");
		
		//한번에 처리 (gson을 이용한 list 전달)
		new Gson().toJson(pi,response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
