package com.gz.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.admin.model.service.CategoryService;
import com.gz.admin.model.vo.Category;

/**
 * Servlet implementation class CategoryUpdateController
 */
@WebServlet("/updateCategory.ad")
public class CategoryUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String categoryName = request.getParameter("name2");
		int categoryNo = Integer.parseInt(request.getParameter("no2"));
		Category c = new Category(categoryNo,categoryName);
		
		Category updateCat = new CategoryService().updateCategory(c);
		
		if(updateCat == null) {
			request.setAttribute("errorMsg", categoryName+"카테고리 수정 실패 !");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			
			session.setAttribute("alertMsg", categoryName+" 카테고리 수정 완료 !");
			response.sendRedirect(request.getContextPath()+"/category.ad");
		}
	}
}