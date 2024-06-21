package com.gz.post.controller.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.post.model.service.PostService;


/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/delete.bo")
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // 삭제할 글번호 추출
	    int postNo = Integer.parseInt(request.getParameter("postNo"));
	    
	    // 글번호로 삭제요청 보내기 
	    int result = new PostService().deletePost(postNo);
	    
	    // 세션 
	    HttpSession session = request.getSession();
	    
	    if (result > 0) {
	        session.setAttribute("alertMsg", "게시글이 삭제되었습니다.");
	    } else {
	        session.setAttribute("alertMsg", "삭제 실패");
	    }
	    
	    // 삭제 후 어디로 갈 지 미리 지정
	    String destination = result > 0 ? "list.bo?currentPage=1" : "detail.bo?postNo=" + postNo;
	    response.sendRedirect(request.getContextPath() + "/" + destination);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
