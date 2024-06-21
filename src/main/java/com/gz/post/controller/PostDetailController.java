package com.gz.post.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.post.model.service.PostService;
import com.gz.post.model.vo.Attachment;
import com.gz.post.model.vo.Post;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet("/detail.bo")
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 정보 조회 + 조회수 증가 처리
	 	int postNo = Integer.parseInt(request.getParameter("postNo"));

	    int result = new PostService().increaseCount(postNo);
	    
	    if(result > 0) {
	    	Post p = new PostService().selectPost(postNo);
	    	ArrayList<Attachment> list = new PostService().selectAttachment(postNo);
	    	System.out.println("detailAt : "+list);
	    	request.setAttribute("p", p);
	    	request.setAttribute("list", list);//첨부파일정보도 전달
	    	request.getRequestDispatcher("views/post/postDetailView.jsp").forward(request, response);
	    }else { //실패
			request.getSession().setAttribute("alertMsg", "게시글 조회 실패");
			response.sendRedirect(request.getHeader("referer"));//이전 주소
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