package com.bb.test;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String gname=request.getParameter("name");
        System.out.println(gname);
        if(gname!=null&& !gname.equals("") && !gname.equals(" ")) {//判断是否有输入
            try {
                //从表中获取垃圾名称
                String sql="select * from garbage where gname=?";
                //调用数据库工具类
                JdbcUtil jdbcUtil=new JdbcUtil(sql);
                jdbcUtil.statement.setString(1,gname);
                ResultSet rs=jdbcUtil.statement.executeQuery();
                if (!rs.next()) {
                    response.getWriter().write("1");
                    System.out.println("未收录此垃圾");
                }
                rs.previous();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    System.out.println("垃圾种类: " + rs.getString(2));
                    System.out.println("垃圾描述: " + rs.getString(3));
                    System.out.println("处理方式: " + rs.getString(4));
                    StringBuilder sb=new StringBuilder();
                    for(int i=0;i<4;i++) {
                        sb.append(rs.getString(i+1));
                        sb.append(" ");
                    }
                    response.getWriter().write(sb.toString());//将所有垃圾信息整合发送
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            response.getWriter().write("2");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
