package com.gz.mypage.folder.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gz.mypage.folder.model.service.FolderService;
import com.gz.mypage.folder.model.vo.Folder;


@WebServlet("/backFolder.fd")
public class BackFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BackFolderController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		int cuFolderNo = Integer.parseInt(request.getParameter("cuFolderNo"));

		ArrayList<Folder> list = new FolderService().selectBackFolder(mno,cuFolderNo);

		if(list != null) {
			//응답
			response.setContentType("json/application; charset=UTF-8");
			//한번에 처리 (gson을 이용한 list 전달)
			new Gson().toJson(list,response.getWriter());
		} else {
			request.setAttribute("errorMsg", "폴더 에러");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
