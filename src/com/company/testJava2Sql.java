package com.company;
import java.sql.*;
public class testJava2Sql {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql:localhost:3306/nbot";
    //���ݿ���û���������
    static final String USER = "Yk";
    static final String PASS = "123456";
    public static void main (String[] args){
        Connection conn = null;
        Statement stmt = null;

        try {
            //ע��JDBC����
            Class.forName(JDBC_DRIVER);
            //������
            System.out.println("�������ݿ�....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //ִ�в�ѯ
            System.out.println("ʵ����Statement���󡣡�����");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id , name , url1 FORM websites";
            ResultSet rs = stmt.executeQuery(sql);
            //չ����������ݿ�
            while(rs.next()){
                //ͨ���ֶμ���
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                //�������
                System.out.println("ID" + id);
                System.out.println("վ�����ƣ�" + name );
                System.out.println("վ��URL: " + url);
                System.out.println("\n");
            }
            //��ɺ�ر�
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //�ر���Դ
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
