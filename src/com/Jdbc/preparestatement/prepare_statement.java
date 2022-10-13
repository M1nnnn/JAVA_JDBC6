package com.Jdbc.preparestatement;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Properties;


public class prepare_statement {
    @Test
    //向customers表中添加一条数据 ;
    public void testInsert() throws IOException, ClassNotFoundException, SQLException, ParseException {
        //读取配置文件的四个基本信息
        //获取类加载器
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            //InputStream is = Connection_finall.class.getClassLoader().getResourceAsStream("jdbc.properties");

            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            //加载驱动
            Class.forName(driverClass);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);

            //对数据库进行操作:预编译sql语句，编译preparedstatement的实例
            String Sql = "insert into customers (name,email,birth)values(?,?,?)";//占位符

            ps = conn.prepareStatement(Sql);
            //填充占位符
            ps.setString(1,"林青霞");
            ps.setString(2,"Null.@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("2001-03-28");
            //ps.setDate(3, (java.sql.Date) new Date(date.getTime()));//sql家的date
           ps.setDate(3,  new java.sql.Date(date.getTime()));//
            //执行sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

                //释放资源
            try {
                if (conn!=null)
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(ps != null)
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
