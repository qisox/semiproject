package com.gz.post.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.category.model.vo.Category;
import com.gz.common.model.vo.PageInfo;
import com.gz.post.model.service.PostService;
import com.gz.post.model.vo.Post;



//꿀팁 게시판 내 카테고리 버튼 누르면 해당 카테고리 내용들만 보여주기
@WebServlet("/ggulPostByCategory.po")
public class ggulPostByCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ggulPostByCategoryController() {
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
		
		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
		
		//Attachment 테이블 내 REF_BNO 가져오기(게시글 리스트중에 Attachment테이블 내 REF_BNO와 게시물 번호가 일치한다면
		//사진게시물이 있는 게시글이란 뜻이기 때문에 사진 아이콘을 붙여주기 위해
		ArrayList<Integer> attList = new PostService().getAttachmentRefBno();
		request.setAttribute("attList", attList);
		

		//카테고리에 따라 getRequestDispatcher의 경로를 다르게 하고, 받아오는 list,pi를 다르게 함.
		
		listCount = new PostService().listCategoryCount(categoryNo); // 해당 카테고리의 게시글 개수 가져오기
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		pageLimit = 10;
		boardLimit = 10;
		
		
		maxPage = (int) Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Post> list = new PostService().selectCategoryList(pi,categoryNo);
		//카테고리 리스트 보내주기
		ArrayList<Category> categoryList = new PostService().getCategoryList();
		// 자유 또는 추천인 요소를 제거한 새로운 리스트 생성
		categoryList.removeIf(category1 -> "자유".equals(category1.getCategoryName()) || "추천".equals(category1.getCategoryName()));
			
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("category", "꿀팁");
		request.setAttribute("categoryNo", categoryNo);
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		System.out.println(pi);
		System.out.println(list);
		request.getRequestDispatcher("views/post/postListView.jsp").forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
