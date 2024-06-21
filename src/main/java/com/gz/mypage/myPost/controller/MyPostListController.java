package com.gz.mypage.myPost.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.myPost.model.service.MyPostService;
import com.gz.mypage.myPost.model.vo.MyPost;


//마이페이지 내가 쓴 글 리스트 가져오는 컨트롤러
@WebServlet("/myPostList.my")
public class MyPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPostListController() {
        super();    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		ArrayList<MyPost> list = new MyPostService().selectMyPostList(mno);
		
		 //응답 
		response.setContentType("json/application; charset=UTF-8"); //한번에 처리
		 //(gson을 이용한 list 전달) 
		 new Gson().toJson(list,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
