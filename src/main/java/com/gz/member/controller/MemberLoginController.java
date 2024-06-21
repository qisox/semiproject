package com.gz.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.member.model.service.MemberService;
import com.gz.member.model.vo.Member;

@WebServlet("/login.me")
public class MemberLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberLoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");

        String saveId = request.getParameter("saveId");

        Cookie cookie = null;

        if (saveId != null && saveId.equals("on")) {
            cookie = new Cookie("memberId", memberId);
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
        } else {
            cookie = new Cookie("memberId", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        Member loginMember = new MemberService().loginMember(memberId, memberPwd);

        HttpSession session = request.getSession();

        String before = request.getHeader("referer");

        if (loginMember == null) {
            session.setAttribute("alertMsg", "로그인 실패");
            response.sendRedirect(before);
        } else {
            session.setAttribute("loginMember", loginMember);
            session.setAttribute("alertMsg", "로그인 성공");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}