package com.bb.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "QuickSearchServlet")
public class QuickSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //管理员快捷查询步骤与前面查询相同只是结果不同,之后可以整合在一起
        String sname=request.getParameter("searchname");
        StringBuilder sgname = new StringBuilder();
        for (int i = 0; i < sname.toCharArray().length; i++) {
            sgname.append("%").append(sname.toCharArray()[i]);
        }
        if (!sname.equals("") && !sname.equals(" ")) {//判断是否有输入
            try {
                //从表中获取垃圾名称
                String sqls = "select * from garbage where gname like ?";
                //调用数据库工具类
                JdbcUtil jdbcUtils = new JdbcUtil(sqls);
                jdbcUtils.statement.setString(1, sgname.toString());
                ResultSet rss = jdbcUtils.statement.executeQuery();
                if (!rss.next()) {
                    response.getWriter().write("1");
                }else {
                    response.getWriter().write("2");
                }

                jdbcUtils.close();
                rss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("3");
//        }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
