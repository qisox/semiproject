package com.gz.mypage.interest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.interest.model.service.InterestService;
import com.gz.mypage.interest.model.vo.Interest;


@WebServlet("/topInterst.in")
public class TopInterestSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TopInterestSelectController() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저번호
		int mno = Integer.parseInt(request.getParameter("mno"));
		ArrayList<Interest> list = new InterestService().selectTopInterest(mno);
		if(list != null) {
			//응답
			response.setContentType("json/application; charset=UTF-8");
			//한번에 처리 (gson을 이용한 list 전달)
			new Gson().toJson(list,response.getWriter());
		} else {
			request.setAttribute("errorMsg", "관심게시글 에러");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
