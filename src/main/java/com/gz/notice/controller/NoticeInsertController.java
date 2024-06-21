package com.gz.notice.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.gz.member.model.vo.Member;
import com.gz.notice.model.service.NoticeService;
import com.gz.notice.model.vo.Notice;
import com.gz.post.model.vo.Attachment;
import com.gz.post.model.vo.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

import oracle.net.aso.r;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/ninsert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/notice/noticeInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String noticeWriter = String.valueOf(((Member)session.getAttribute("loginMember")).getMemberNo());
		
		Notice n = new Notice();
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		n.setNoticeWriter(noticeWriter);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result>0) {
			session.setAttribute("alertMsg", "공지사항 작성 성공");
			response.sendRedirect(request.getContextPath()+"/nlist.no?currentPage=1");
		}else {
			session.setAttribute("alertMsg", "공지사항 작성 실패");
			response.sendRedirect(request.getContextPath()+"/nlist.no?currentPage=1");
		}
		
	}
}
