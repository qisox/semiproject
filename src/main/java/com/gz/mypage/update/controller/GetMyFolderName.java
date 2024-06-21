package com.gz.mypage.update.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.folder.model.vo.Folder;
import com.gz.mypage.update.model.service.MoveService;


@WebServlet("/getMyFolderName.my")
public class GetMyFolderName extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetMyFolderName() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		System.out.println("inGetMyFolder");
		ArrayList<Folder> list = new MoveService().selectFolderList(mno);
		System.out.println(list);

		//응답
		response.setContentType("json/application; charset=UTF-8");
		
		//한번에 처리 (gson을 이용한 list 전달)
		new Gson().toJson(list,response.getWriter());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
