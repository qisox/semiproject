package com.gz.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.category.model.vo.Category;
import com.gz.common.model.vo.PageInfo;
import com.gz.post.model.service.PostService;
import com.gz.post.model.vo.Post;


/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int listCount; 
		int currentPage; 
		int pageLimit; 
		int boardLimit; 

		int maxPage; 
		int startPage; 
		int endPage ;
		
		String category = request.getParameter("category");
		
		//Attachment 테이블 내 REF_BNO 가져오기(게시글 리스트중에 Attachment테이블 내 REF_BNO와 게시물 번호가 일치한다면
		//사진게시물이 있는 게시글이란 뜻이기 때문에 사진 아이콘을 붙여주기 위해
		ArrayList<Integer> attList = new PostService().getAttachmentRefBno();
		// 중복 제거
        HashSet<Integer> set = new HashSet<>(attList);
        ArrayList<Integer> uniqueList = new ArrayList<>(set);
		request.setAttribute("attList", uniqueList);
		
		System.out.println("attList : "+uniqueList );
		
		if(category == null) {
			category = "꿀팁";//null이라면 default로 일단 꿀팁으로 두기
		}

		System.out.println(category);
		//카테고리에 따라 getRequestDispatcher의 경로를 다르게 하고, 받아오는 list,pi를 다르게 함.
		if(category.equals("추천")) {
			listCount = new PostService().listRecommendCount(); // 게시글 개수 조회해오기
			
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
			
			ArrayList<Post> list = new PostService().selectRecommendList(pi);
			
			
			request.setAttribute("category", category);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			System.out.println(pi);
			System.out.println(list);
			request.getRequestDispatcher("views/post/RecommPostListView.jsp").forward(request, response);
		} else if(category.equals("자유")) {
			listCount = new PostService().listFreeCount(); // 게시글 개수 조회해오기
			
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
			
			ArrayList<Post> list = new PostService().selectFreeList(pi);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			System.out.println(pi);
			System.out.println(list);
			request.getRequestDispatcher("views/post/FreePostListView.jsp").forward(request, response);
			
		} else {
			listCount = new PostService().listCount(); // 게시글 개수 조회해오기
			
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
			
			ArrayList<Post> list = new PostService().selectList(pi);
			//카테고리 리스트 보내주기
			ArrayList<Category> categoryList = new PostService().getCategoryList();
			//자유/추천을 제외한 카테고리 리스트로
			System.out.println("categoryList1 : "+categoryList);
			// 자유 또는 추천인 요소를 제거한 새로운 리스트 생성
			categoryList.removeIf(category1 -> "자유".equals(category1.getCategoryName()) || "추천".equals(category1.getCategoryName()));
	
			
			System.out.println("categoryList : "+categoryList);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			System.out.println(pi);
			System.out.println(list);
			request.getRequestDispatcher("views/post/postListView.jsp").forward(request, response);
			
		}
		
			
			
	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
