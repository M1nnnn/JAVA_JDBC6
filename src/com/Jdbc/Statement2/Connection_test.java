package com.Jdbc.Statement2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection_test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //ע������
        Class.forName("com.mysql.cj.jdbc.Driver");
        //��ȡ����
        String passward = "abcd4567";
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String username = "root";
        Connection conn = DriverManager.getConnection(url, username, passward);

    //����SQL
        String sql = "UPDATE myemplo SET salary = 2000 WHERE id = 2";
        //��ȡִ��sql����
        Statement stmt = conn.createStatement();

        //ִ��sql
        int count = stmt.executeUpdate(sql);
        //������
        System.out.println(count);
        System.out.println(conn);
        //�ͷ���Դ
        stmt.close();
        conn.close();
    }

}
