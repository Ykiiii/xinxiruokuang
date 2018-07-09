package com.company;
import java.sql.*;
public class testJava2Sql {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql:localhost:3306/nbot";
    //数据库的用户名和密码
    static final String USER = "Yk";
    static final String PASS = "123456";
    public static void main (String[] args){
        Connection conn = null;
        Statement stmt = null;

        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            System.out.println("连接数据库....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //执行查询
            System.out.println("实例化Statement对象。。。。");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id , name , url1 FORM websites";
            ResultSet rs = stmt.executeQuery(sql);
            //展开结果集数据库
            while(rs.next()){
                //通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                //输出数据
                System.out.println("ID" + id);
                System.out.println("站点名称：" + name );
                System.out.println("站点URL: " + url);
                System.out.println("\n");
            }
            //完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if(stmt!=null) stmt.close();
            } catch (SQLException e) {
                //nothing to do
            }
            try{
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("BYE");
    }
}
