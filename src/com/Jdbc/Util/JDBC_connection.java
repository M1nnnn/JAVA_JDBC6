package com.Jdbc.Util;

import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JDBC_connection {

    //�����ݿ����ӷ�װ�ɷ���
    public static Connection getConnection() throws Exception{
        //��ȡ�����ļ����ĸ�������Ϣ
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //��������
        Class.forName(driverClass);
        //��ȡ����
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

        return conn;
    }

    //�ر���Դ
    public static void clossResource (Connection conn, PreparedStatement ps){
        //�ͷ���Դ
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

    //��ѯ�����ر�resultSet��Դ
    public  static  void closeCourse(Connection conn, PreparedStatement ps, ResultSet resultSet){
        //�ͷ���Դ
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
     * ��������: <br>
     * ʹ��Dbutils.jar���еĹ�����ʵ����Դ�ر�
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/12 0:07
     */
    public  static  void closeCourseDbutils(Connection conn, PreparedStatement ps, ResultSet resultSet) {
        //�ͷ���Դ
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
//��ʽһ���ᱨ�쳣
        /*DbUtils.close(conn);
        DbUtils.close(resultSet);
        DbUtils.close(ps);*/
//��ʽ���������쳣
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(resultSet);
    }
}
