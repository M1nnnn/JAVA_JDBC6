package com.Jdbc.Statement2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection_test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        String passward = "abcd4567";
        String url = "jdbc:mysql://localhost:3306/jdbc_test";
        String username = "root";
        Connection conn = DriverManager.getConnection(url, username, passward);

    //定义SQL
        String sql = "UPDATE myemplo SET salary = 2000 WHERE id = 2";
        //获取执行sql对象
        Statement stmt = conn.createStatement();

        //执行sql
        int count = stmt.executeUpdate(sql);
        //处理结果
        System.out.println(count);
        System.out.println(conn);
        //释放资源
        stmt.close();
        conn.close();
    }

}
