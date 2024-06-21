package com.gz.mypage.interest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.mypage.interest.model.service.InterestService;


@WebServlet("/addFolder.my")
public class FolderAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FolderAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		String folderName = request.getParameter("folderName");
		int currentFolderNo = Integer.parseInt(request.getParameter("currentFolderNo"));
		
		int result = new InterestService().addFolder(mno,folderName,currentFolderNo);
		if(result > 0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
