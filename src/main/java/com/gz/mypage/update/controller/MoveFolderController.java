package com.gz.mypage.update.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gz.mypage.update.model.service.MoveService;


@WebServlet("/moveFolder.my")
public class MoveFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MoveFolderController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		int targetFolderNo = Integer.parseInt(request.getParameter("targetFolderNo"));
		String[] folderNoArr = request.getParameterValues("folderNoArr[]");
		String[] postNoArr = request.getParameterValues("postNoArr[]");
		
		//내가 이동한다고 선택한 폴더로 이동할 번호를 지정했는지 아닌지
		boolean isConflict = false;
		//내가 이동한다고 선택한 폴더로 이동할 번호를 지정하면 바록 fail을 보내도록
		if(folderNoArr != null) {
			for(String f : folderNoArr) {
				if(f.equals(String.valueOf(targetFolderNo))) {
					isConflict = true;
					break;
				}
			}
		}
		
		
		//만약 선택한 폴더번호와 이동대상 폴더번호가 일치한다면
		if(isConflict) {
			response.getWriter().print("CONFLICT");
		}else {
			int result = new MoveService().moveFolder(mno,targetFolderNo,folderNoArr,postNoArr);
			if(result > 0) {
				response.getWriter().print("NNNNY");
			} else {
				response.getWriter().print("NNNNN");
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
