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
 * Servlet implementation class CategoryInsertController
 */
@WebServlet("/insertCategory.ad")
public class CategoryInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/admin/categoryForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        int no = Integer.parseInt(request.getParameter("no"));
        String name = request.getParameter("name");
        
        Category c = new Category();
        c.setCategoryNo(no);
        c.setCategoryName(name);
        
        int result = new CategoryService().insertCategpry(c);
        
        if(result>0) {
            request.setAttribute("alertMsg", "카테고리 추가 성공");
            response.sendRedirect(request.getContextPath()+"/category.ad");
        }else {
            request.setAttribute("alertMsg","카테고리 추가 실패");
            response.sendRedirect(request.getContextPath()+"/category.ad");
        }
        response.getWriter().print(c);
	}
}
