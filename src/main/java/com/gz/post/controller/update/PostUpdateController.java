package com.gz.post.controller.update;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.gz.post.model.service.PostService;
import com.gz.post.model.vo.Attachment;
import com.gz.category.model.vo.Category;
import com.gz.post.model.vo.MyFileRenamePolicy;
import com.gz.post.model.vo.Post;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		PostService bs = new PostService();
		//수정페이지에 띄워줄 데이터 조회해오기
		Post p = bs.selectPost(postNo);
		//카테고리 목록
		ArrayList<Category> list = bs.selectCategoryList(); 
		//첨부파일 정보 
		ArrayList<Attachment> atList = bs.selectAttachment(postNo);
		
		
		request.setAttribute("p", p);
		request.setAttribute("cList", list);
		request.setAttribute("atList", atList);
		
		request.getRequestDispatcher("views/post/postUpdateForm.jsp").forward(request, response);
		
		
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
			
			int postNo = Integer.parseInt(multiRequest.getParameter("postNo"));
			String category = multiRequest.getParameter("category");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
					
			Post p = new Post();
			p.setPostNo(postNo);
			p.setContent(content);
			p.setTitle(title);
			p.setCategory(category);
			
			//만약 새로운 첨부파일이 전달되었다면 
			Attachment at = null;
			ArrayList<Attachment> originList = new ArrayList<Attachment>();
			ArrayList<Attachment> newList = new ArrayList<Attachment>();
			//기존첨부파일이 있을때 새로운 첨부파일이 들어온경우 (기존 첨부파일 삭제해주기)
			boolean[] flag = {false,false,false,false};
			for(int i=0;i<4;i++) {
				//키값
				String key = "reUploadFile"+i;
				String key1 = "newUploadFile"+i;
				//키값에 해댕하는 요소가 있는지 확인하기
				//새로 전달된 파일명이 있을때
				System.out.println("^^^^"+multiRequest.getOriginalFileName(key));
				System.out.println(multiRequest.getFile(key)); 
				System.out.println(multiRequest.getFile(key1));
				System.out.println(multiRequest.getOriginalFileName(key1));  
				if(multiRequest.getOriginalFileName(key) != null) {
					System.out.println("asd");
					at = new Attachment(); //객체 생성
					//전달된 파일명 
					at.setOriginName(multiRequest.getOriginalFileName(key));
					//서버에 업로드된 파일명
					at.setChangeName(multiRequest.getFilesystemName(key));
					//저장 경로 넣기
					at.setFilePath("/resources/uploadFiles/");
					
					//만약 기존에도 파일이 있었다면 파일번호와 파일명을 전달받기 
					if(multiRequest.getParameter("originFileNo"+i) != null) {
						
						at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo"+i)));
						
						//기존 첨부파일 삭제 플래그
						flag[i] = true;
						
					}else {//새로 전달된 파일은 있지만 기존에는 파일이 없었을때 
						//데이터베이스에 Attachment정보를 추가해야한다.
						//참조 게시글 번호를 세팅 
						at.setRefBno(postNo);
					}
					originList.add(at);
				}else if(multiRequest.getOriginalFileName(key1) != null) {
					at = new Attachment();
					at.setRefBno(postNo);
					at.setOriginName(multiRequest.getOriginalFileName(key1));
					at.setChangeName(multiRequest.getFilesystemName(key1));
					at.setFilePath("/resources/uploadFiles/");
					
					newList.add(at); //값 추출 끝났으니 리스트에 추가하기.
				}
				
				
			}


			
			int result = new PostService().updatePost(p,originList,1);
			int result2 = new PostService().updatePost(p,newList,2);
			HttpSession session = request.getSession();
			//성공시에 상세페이지로 성공메세지와 함께
			System.out.println("zz"+result);
			System.out.println("zz"+result2);
			if(result>0) {
				
				//기존에 있었던 파일이 지워져야한다면 
				for(int i=0;i<4;i++) {
					if(flag[i]) {
						//기존 파일 삭제 해주기 (저장경로 + 원본파일명)-원본파일명은 view에서 넘겨줌
						new File(savePath+multiRequest.getParameter("originFileName"+i)).delete();
						System.out.println(multiRequest.getParameter("originFileName"+i));
					}
				}
				
				session.setAttribute("alertMsg", "게시글 수정 성공");
				response.sendRedirect(request.getContextPath()+"/detail.bo?postNo="+postNo);
			}else {
				session.setAttribute("alertMsg", "게시글 수정 실패");
				response.sendRedirect(request.getContextPath()+"/detail.bo?postNo="+postNo);
			}
	
		}

		
	}

}
