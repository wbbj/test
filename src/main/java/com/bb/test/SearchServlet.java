package com.bb.test;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //管理员快捷查询
//        String sname=request.getParameter("searchname");
//        System.out.println(sname);
//        StringBuilder sgname=new StringBuilder();
//        for(int i=0;i<sname.toCharArray().length;i++){
//            sgname.append("%").append(sname.toCharArray()[i]);
//        }
//        if(!sname.equals("") && !sname.equals(" ")) {//判断是否有输入
//            try {
//                //从表中获取垃圾名称
//                String sqls="select * from garbage where gname like ?";
//                //调用数据库工具类
//                JdbcUtil jdbcUtils=new JdbcUtil(sqls);
//                jdbcUtils.statement.setString(1,sgname.toString());
//                ResultSet rss=jdbcUtils.statement.executeQuery();
//                if (!rss.next()) {
//                    response.getWriter().write("1");
//                }
//                rss.previous();
//                while (rss.next()) {
//                    response.getWriter().write("2");
//                }
//                jdbcUtils.close();
//                rss.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            response.getWriter().write("3");
//        }


        //用户的查询
        String aname=request.getParameter("name");
        StringBuilder name=new StringBuilder();
        for(int i=0;i<aname.toCharArray().length;i++){
            name.append("%").append(aname.toCharArray()[i]);
        }

        if(!aname.equals("") && !aname.equals(" ")) {//判断是否有输入
            try {
                //从表中获取垃圾名称
                String sql="select * from garbage where gname like ?";
                //调用数据库工具类
                JdbcUtil jdbcUtil=new JdbcUtil(sql);
                jdbcUtil.statement.setString(1,name.toString());
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
                jdbcUtil.close();

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
