package com.bb.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name = "SearchServlet",urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //用户的查询
        //接收查询的信息
        String aname=request.getParameter("name");
        //将信息分割整合为模糊查询的形式
        StringBuilder name=new StringBuilder();
        for(int i=0;i<aname.toCharArray().length;i++){
            name.append("%").append(aname.toCharArray()[i]);
        }
        name.append("%");
        //开始判断信息
        if(!aname.equals("") && !aname.equals(" ")) {//判断是否有输入
            try {
                //从表中获取垃圾名称
                String sql="select * from garbage where gname like ?";
                //调用数据库工具类
                JdbcUtil jdbcUtil=new JdbcUtil(sql);
                jdbcUtil.statement.setString(1,name.toString());
                ResultSet rs=jdbcUtil.statement.executeQuery();
                //获取结果,如果没有返回响应数据"1"
                if (!rs.next()) {
                    response.getWriter().write("1");
                    System.out.println("未收录此垃圾");
                }
                //这里回调一下防止执行了上面继续进行多余动作
                rs.previous();
                //有垃圾,将信息整合
                while (rs.next()) {
                    StringBuilder sb=new StringBuilder();
                    //这里数据库中表内有四条数据
                    for(int i=0;i<4;i++) {
                        sb.append(rs.getString(i+1));
                        sb.append(" ");
                    }
                    response.getWriter().write(sb.toString());//将所有垃圾信息整合发送
                }
                //调用关闭类
                jdbcUtil.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            //没输入数据返回"2"
            response.getWriter().write("2");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
