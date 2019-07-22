package com.bb.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @Classname: JdbcUtils
 * @author Firedance
 * @version 2018年5月16日 下午8:04:55
 *
 */
public class JdbcUtil {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties prop = new Properties();
        try {
            //获取外部文件"jdbc.properties"的资源流
            prop.load(JdbcUtil.class.getClassLoader().getResourceAsStream("/jdbc.properties"));
            //从资源流里获取各个属性的值
            driver=prop.getProperty("driver");
            url=prop.getProperty("url");
            user=prop.getProperty("user");
            password=prop.getProperty("password");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            //加载数据库驱动
            Class.forName(driver);
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //get链接方法
    static Connection getConnection(){
        Connection conn = null;
        try {
            //获取数据库链接
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接成功");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    //关流方法
    static void close(ResultSet res, PreparedStatement pst, Connection conn) {
        if(null !=res) {
            try {
                res.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(null !=pst) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(null !=conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}