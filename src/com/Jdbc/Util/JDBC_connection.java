package com.Jdbc.Util;

import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JDBC_connection {

    //将数据库连接封装成方法
    public static Connection getConnection() throws Exception{
        //读取配置文件的四个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

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

        return conn;
    }

    //关闭资源
    public static void clossResource (Connection conn, PreparedStatement ps){
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

    //查询操作关闭resultSet资源
    public  static  void closeCourse(Connection conn, PreparedStatement ps, ResultSet resultSet){
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
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 功能描述: <br>
     * 使用Dbutils.jar包中的工具类实现资源关闭
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/12 0:07
     */
    public  static  void closeCourseDbutils(Connection conn, PreparedStatement ps, ResultSet resultSet) {
        //释放资源
       /* try {
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
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    */
//方式一，会报异常
        /*DbUtils.close(conn);
        DbUtils.close(resultSet);
        DbUtils.close(ps);*/
//方式二：不报异常
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(resultSet);
    }
}
