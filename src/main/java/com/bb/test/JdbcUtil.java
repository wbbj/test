package com.bb.test;

import java.sql.*;

public class JdbcUtil {

    private Connection connection = null;
    PreparedStatement statement = null;
    Statement statement1=null;
    JdbcUtil(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://129.211.1.53:3306/refuse_classification?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","Wbb.367494");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/refuse_classification?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","367494");
            statement = connection.prepareStatement(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    JdbcUtil(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://129.211.1.53:3306/refuse_classification?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","Wbb.367494");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/refuse_classification?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","367494");
            statement1=connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    void close(){
        if (this.statement1 != null) {
            try {
                this.statement1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (this.statement != null) {
            try {
                this.statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
