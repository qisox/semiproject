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


@WebServlet("/topFolder.fd")
public class TopFolderSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TopFolderSelectController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저번호
		int mno = Integer.parseInt(request.getParameter("mno"));
		ArrayList<Folder> fList = new FolderService().selectFolder(mno);
		if(fList != null) {
			//응답
			response.setContentType("json/application; charset=UTF-8");
			//한번에 처리 (gson을 이용한 list 전달)
			new Gson().toJson(fList,response.getWriter());
		} else {
			request.setAttribute("errorMsg", "폴더 에러");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
