package com.bb.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String sessionId=request.getRequestedSessionId();

        System.out.println(username);
        System.out.println(password);

        try {

            String sql="SELECT count(id) FROM test_login WHERE username=? AND password=?";
            JdbcUtil jdbcUtil=new JdbcUtil(sql);
            jdbcUtil.statement.setString(1,username);
            jdbcUtil.statement.setString(2,password);
            ResultSet resultSet=jdbcUtil.statement.executeQuery();

            if(resultSet.next()) {

                int count = resultSet.getInt(1);
                if (count > 0) {
                    Cookie cookie = new Cookie("sessionId", sessionId);

                    response.addCookie(cookie);
                    cookie.setMaxAge(60);
                    request.getSession().setAttribute("success", true);
                    request.getSession().setMaxInactiveInterval(60);
                    response.getWriter().write("1");

                } else {
                    response.getWriter().write("2");

                }
            }
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
