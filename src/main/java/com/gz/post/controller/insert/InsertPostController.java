package com.gz.post.controller.insert;
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

import com.gz.category.model.vo.Category;
import com.gz.member.model.vo.Member;
import com.gz.post.model.service.PostService;
import com.gz.post.model.vo.Attachment;
import com.gz.post.model.vo.MyFileRenamePolicy;
import com.gz.post.model.vo.Post;
import com.oreilly.servlet.MultipartRequest;
//게시판 작성 컨트롤러
@WebServlet("/insert.bo")
public class InsertPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Category> category = new PostService().getCategoryList();
		request.setAttribute("category", category);
		request.getRequestDispatcher("views/insert/PostInsertForm.jsp").forward(request, response);
		
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
			
			//기존 rquest객체로는 파일전달을 받을 수 없으니 MultipartRequest 객체로 변환하는 작업
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			int categoryNo = Integer.parseInt(multiRequest.getParameter("categoryNo"));
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String categoryName = multiRequest.getParameter("category");
			System.out.println("categoryName : "+categoryName);
			//작성자 정보
			int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();

			Post p = new Post();
			p.setMemberNo(memberNo);
			p.setContent(content);
			p.setTitle(title);
			p.setCategoryNo(categoryNo);
			
			System.out.println(categoryNo);
			System.out.println(title);
			System.out.println(content);
			ArrayList<Attachment> list = new ArrayList<>();
			
			//각 키값을 반복 돌리면서 요소 꺼내주기
			
			for(int i=1;i<=4;i++) {
				//키값
				String key = "file"+i;
				
				//키값에 해댕하는 요소가 있는지 확인하기
				if(multiRequest.getOriginalFileName(key)!=null) {
					//해당 키값에 파일이 있다면
					//Attachment 객체 생성 후 데이터 담아주기
					//여러개가 나올 수 있으니 Attachment객체를 list에 추가하기
					//원본명,변경이름,파일경로,파일레벨(썸네일사진/상세사진)
					
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("/resources/uploadFiles/");
					
					list.add(at); //값 추출 끝났으니 리스트에 추가하기.
				}
			}
			
			System.out.println("attachList : "+list);
			int result = new PostService().insertPost(p, list);
			//System.out.println(result);
			
			if (result > 0) {
				session.setAttribute("alertMsg", "게시글 등록 성공");
				
				response.sendRedirect(request.getContextPath() + "/list.bo?currentPage=1&category="+categoryName);
			} else {

				session.setAttribute("alertMsg", "게시글 등록 실패");
				
				response.sendRedirect(request.getContextPath() + "/list.bo?currentPage=1&category="+categoryName);
				
			}
		}
	}
}