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
import com.gz.post.model.service.PostService;
import com.gz.complain.model.vo.ComAttachment;
import com.gz.complain.model.vo.Complain;
import com.gz.post.model.vo.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ComplainUpdateController
 */
@WebServlet("/complainUpdate.bo")
public class ComplainUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplainUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int complainNo = Integer.parseInt(request.getParameter("complainNo"));
		
		//수정페이지에 띄워줄 데이터 조회
		Complain c = new ComplainService().selectComplain(complainNo);
		//첨부파일 정보
		ComAttachment at = new ComplainService().selectAttachment2(complainNo);
		
		request.setAttribute("c", c);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/complain/complainUpdate.jsp").forward(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");

			MultipartRequest multiRequest 
					= new MultipartRequest(request, savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
			
			int complainNo = Integer.parseInt(multiRequest.getParameter("complainNo"));
			String complainCategory = multiRequest.getParameter("category");
			String complainTitle = multiRequest.getParameter("title");
			String complainContent = multiRequest.getParameter("content");
			
			
					
			Complain c = new Complain();
			c.setComplainNo(complainNo);
			c.setComplainCategory(complainCategory);;
			c.setComplainTitle(complainTitle);
			c.setComplainContent(complainContent);
			
			//만약 새로운 첨부파일이 전달되었다면 
			ComAttachment at = null;
			
			//기존첨부파일이 있을때 새로운 첨부파일이 들어온경우 (기존 첨부파일 삭제해주기)
			boolean flag = false;
			
			//새로 전달된 파일명이 있을때
			if(multiRequest.getOriginalFileName("reUploadFile") != null) {
				at = new ComAttachment(); //객체 생성
				//전달된 파일명 
				at.setOriginName(multiRequest.getOriginalFileName("reUploadFile"));
				//서버에 업로드된 파일명
				at.setChangeName(multiRequest.getFilesystemName("reUploadFile"));
				//저장 경로 넣기
				at.setFilePath("/resources/uploadFiles/");
				
				//만약 기존에도 파일이 있었다면 파일번호와 파일명을 전달받기 
				if(multiRequest.getParameter("originFileNo") != null) {
					
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					//기존 첨부파일 삭제 플래그
					flag = true;
					
				}else {//새로 전달된 파일은 있지만 기존에는 파일이 없었을때 
					//데이터베이스에 Attachment정보를 추가해야한다.
					//참조 게시글 번호를 세팅 
					at.setRefBno(complainNo);
				}
				
			}
			
			int result = new ComplainService().updateComplain(c,at);
			HttpSession session = request.getSession();
			//성공시에 상세페이지로 성공메세지와 함께
			if(result>0) {
				
				//기존에 있었던 파일이 지워져야한다면 
				if(flag) {
					//기존 파일 삭제 해주기 (저장경로 + 원본파일명)-원본파일명은 view에서 넘겨줌
					new File(savePath+multiRequest.getParameter("originFileName")).delete();
				}
				
				session.setAttribute("alertMsg", "게시글 수정 성공");
				response.sendRedirect(request.getContextPath()+"/complainList.bo?complainNo="+complainNo);
			}else {
				session.setAttribute("alertMsg", "게시글 수정 실패");
				response.sendRedirect(request.getContextPath()+"/complainList.bo?complainNo="+complainNo);
			}
	
		}

		
	}
	}


