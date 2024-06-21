package com.gz.post.controller.increase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gz.common.JDBCTemplate;
import com.gz.member.model.vo.Member;

/**
 * Servlet implementation class IncreaseInterestCountServlet
 */

@WebServlet("/increaseInterestCount.po")
public class IncreaseInterestCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IncreaseInterestCountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	        try {
	            int postNo = Integer.parseInt(request.getParameter("postNo"));

            // 세션을 사용하여 사용자의 좋아요 상태를 확인
            HttpSession session = request.getSession();

            // 로그인이 되어 있는지 확인
            Member loginMember = (Member) session.getAttribute("loginMember");
            if (loginMember == null) {
                response.getWriter().write("error");
                return;
            }

            int userNo = loginMember.getMemberNo();

            Set<String> interestPosts = (Set<String>) session.getAttribute("interestPosts");

            if (interestPosts == null) {
            	interestPosts = new HashSet<>();
                session.setAttribute("interestPosts", interestPosts);
            }

            String interestKey = userNo + "_" + session.getId() + "_" + postNo;

            if (interestPosts.contains(interestKey)) {
                // 이미 관심을 눌렀을 경우
                response.getWriter().write("error");
            } else {
                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    conn = JDBCTemplate.getConnection();

                    String sql = "UPDATE POST SET INTEREST_COUNT = INTEREST_COUNT + 1 WHERE POST_NO = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, postNo);

                    int result = pstmt.executeUpdate();

                    if (result > 0) {
                        // 관심 수가 성공적으로 업데이트되면 좋아요 수를 응답으로 보냄
                    	interestPosts.add(interestKey); // 게시물에 대한 좋아요를 사용자 세션에 추가
                        session.setAttribute("interestPosts", interestPosts);
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("error");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.getWriter().write("error");
                } finally {
                    JDBCTemplate.close(pstmt);
                    JDBCTemplate.close(conn);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }
}
    
