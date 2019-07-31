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
        //获取账号密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String sessionId=request.getRequestedSessionId();

        try {
            //数据库查询语句
            String sql="SELECT count(id) FROM test_login WHERE username=? AND password=?";
            JdbcUtil jdbcUtil=new JdbcUtil(sql);
            jdbcUtil.statement.setString(1,username);
            jdbcUtil.statement.setString(2,password);
            ResultSet resultSet=jdbcUtil.statement.executeQuery();
            //有数据
            if(resultSet.next()) {
                //判断是否相同
                int count = resultSet.getInt(1);
                if (count > 0) {
                    //设置Cookie
                    Cookie cookie = new Cookie("sessionId", sessionId);
                    //添加Cookie
                    response.addCookie(cookie);
                    //设置Cookie最大时限
                    cookie.setMaxAge(60);
                    //设置Session
                    request.getSession().setAttribute("success", true);
                    //设置Session的最大时限
                    request.getSession().setMaxInactiveInterval(60);
                    //返回响应数据
                    response.getWriter().write("1");

                } else {
                    //不同返回"2"
                    response.getWriter().write("2");

                }
            }
            //关闭
            jdbcUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
