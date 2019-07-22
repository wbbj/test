package com.bb.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String sessionId=request.getRequestedSessionId();

        System.out.println(username);
        System.out.println(password);
        if(username.equals("wbb")&&password.equals("367494")){
            Cookie cookie = new Cookie("sessionId", sessionId);

                    response.addCookie(cookie);
                    cookie.setMaxAge(60);
                    request.getSession().setAttribute("success", true);
                    request.getSession().setMaxInactiveInterval(60);
                    response.getWriter().write("1");

        }
        else {
            response.getWriter().write("2");

        }
//        Connection connection;
//        PreparedStatement statement;
//        ResultSet resultSet;
//
//        try {
//            connection=JdbcUtil.getConnection();
//            System.out.println("数据量连接成功");
//            String sql="SELECT count(id) FROM test_login WHERE username=?" +
//                    "AND password=?";
//
//            statement=connection.prepareStatement(sql);
//            statement.setString(1,username);
//            statement.setString(2,password);
//            resultSet=statement.executeQuery();
//            if(resultSet.next()){
//                int count=resultSet.getInt(1);
//                if(count>0){
//                    Cookie cookie = new Cookie("username", username);
//                    response.addCookie(cookie);
//                    cookie.setMaxAge(60);
//                    request.getSession().setAttribute("success", true);
//                    response.sendRedirect("index.html");
//                }else {
//                    response.sendRedirect("login.html");
//                }
//            }
//
//            JdbcUtil.close(resultSet,statement, connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
