package com.bb.test;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "SearchMyServlet",urlPatterns = "/SearchMyServlet")
public class SearchMyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String gname=request.getParameter("name");
        System.out.println(gname);

        try {
            String sql="SELECT * FROM garbage where gname=?";
            JdbcUtil jdbcUtil=new JdbcUtil(sql);
            jdbcUtil.statement.setString(1,gname);
            ResultSet rs=jdbcUtil.statement.executeQuery();
            while (rs.next()){

                System.out.println(rs.getString("gname")+" "+rs.getString("variety"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
