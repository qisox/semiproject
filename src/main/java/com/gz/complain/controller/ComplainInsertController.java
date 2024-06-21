package com.gz.complain.controller;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.gz.complain.model.service.ComplainService;
import com.gz.member.model.vo.Member;
import com.gz.complain.model.vo.ComAttachment;
import com.gz.complain.model.vo.Complain;
import com.gz.post.model.vo.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
//게시판 작성 컨트롤러
@WebServlet("/complainInsert.bo")
public class ComplainInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("views/complain/complainInsertForm.jsp").forward(request, response);
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if (ServletFileUpload.isMultipartContent(request)) {
			//파일 업로드를 위한 라이브러리 cos.jar
			
			//전송되는 파일을 처리할 작업 내용
			int maxSize = 10 * 1024 * 1024;
			
			//전달된 파일을 저장할 서버의 경로
			//C:\Users\0808t\Downloads\Board\src\main\webapp\
			String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
			System.out.println(savePath);
			
			//기존 rquest객체로는 파일전달을 받을 수 없으니 MultipartRequest 객체로 변환하는 작업
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String complainCategory = multiRequest.getParameter("categoryNo");
			String complainTitle = multiRequest.getParameter("title");
			String complainContent = multiRequest.getParameter("content");
			
			//작성자 정보
			String complainWriter = ((Member)session.getAttribute("loginMember")).getNickname();

			Complain c = new Complain();
			c.setComplainCategory(complainCategory);
			c.setComplainTitle(complainTitle);
			c.setComplainContent(complainContent);
			c.setComplainWriter(complainWriter);
			

			

		
			ComAttachment at = null;  //첨부파일이 있다면 담아줌
			System.out.println(multiRequest.getOriginalFileName("uploadFile"));
			if(multiRequest.getOriginalFileName("uploadFile") != null) {
				at = new ComAttachment();
				at.setOriginName(multiRequest.getOriginalFileName("uploadFile"));
				at.setChangeName(multiRequest.getFilesystemName("uploadFile"));
				at.setFilePath("/resources/uploadFiles/");
			}
			System.out.println(at);
			int result = new ComplainService().insertComplain(c, at);
			//System.out.println(result);
			
			if (result > 0) {
				session.setAttribute("alertMsg", "게시글 등록 성공");
				
				response.sendRedirect(request.getContextPath() + "/complainList.bo?currentPage=1");
			} else {
				if(at != null) {
					new File(savePath + at.getChangeName()).delete();
				}
				session.setAttribute("alertMsg", "게시글 등록 실패");
				
				response.sendRedirect(request.getContextPath() + "/complainList.bo?currentPage=1");
				
			}
		}
	}
}