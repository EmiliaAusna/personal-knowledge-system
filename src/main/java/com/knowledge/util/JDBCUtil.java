package com.knowledge.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBCUtil {
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties prop = new Properties();
        try(InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties")){
            prop.load(is);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(url,user,password);
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{if(rs!=null) rs.close();}catch (Exception e){}
        try{if(pstmt!=null) pstmt.close();}catch (Exception e){}
        try{if(conn!=null) conn.close();}catch (Exception e){}
    }
    public static void close(Connection conn, PreparedStatement pstmt){
        close(conn,pstmt,null);
    }
}
