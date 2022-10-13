package com.Jdbc.preparestatement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Connection_final {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

    //最终连接方法：讲数据库连接的四个基本条件放在配置文件中
   // public void Connection_test2() throws Exception{
//      读取配置文件的四个基本信息
        InputStream is = Connection_final.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);
        //获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }
}
