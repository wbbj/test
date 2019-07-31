package com.bb.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AddServlet" ,urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //获取前台请求的垃圾的所有数据
        String gname=request.getParameter("gname");
        String variety=request.getParameter("variety");
        String describe=request.getParameter("describe");
        String handle=request.getParameter("handle");;
        //整合模糊查询
        StringBuilder name=new StringBuilder();
        for(int i=0;i<gname.toCharArray().length;i++){
            name.append("%").append(gname.toCharArray()[i]);
        }
        name.append("%");

        //判断是否有输入
        if(!gname.equals("") && !gname.equals(" ")&&!variety.equals("") && !variety.equals(" ")&&!describe.equals("") && !describe.equals(" ")&&!handle.equals("") && !handle.equals(" ")) {//判断是否有输入
            try {
                //从表中获取垃圾名称
                String sql="select * from garbage where gname like ?";
                //调用数据库工具类
                JdbcUtil jdbcUtil=new JdbcUtil(sql);
                jdbcUtil.statement.setString(1,name.toString());

                ResultSet rs=jdbcUtil.statement.executeQuery();
                //如果库中没有此垃圾,执行以下操作
                if (!rs.next()) {
                    try {
                        JdbcUtil jdbcUtil1 = new JdbcUtil();
                        String sql1 = "insert into garbage values('" +
                                gname +
                                "','" +
                                variety +
                                "','" +
                                describe +
                                "','" +
                                handle +
                                "')";

                        jdbcUtil1.statement1.execute(sql1);
                        //插入成功返回一个响应以提醒插入成功
                        response.getWriter().write("1");
                        jdbcUtil1.close();

                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }

                }
                rs.previous();
                //数据库已存在所插入数据的情况
                while (rs.next()) {
                    jdbcUtil.close();
                    rs.close();
                    response.getWriter().write("2");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            response.getWriter().write("3");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
