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
    //��customers�������һ������ ;
    public void testInsert() throws IOException, ClassNotFoundException, SQLException, ParseException {
        //��ȡ�����ļ����ĸ�������Ϣ
        //��ȡ�������
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

            //��������
            Class.forName(driverClass);
            //��ȡ����
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);

            //�����ݿ���в���:Ԥ����sql��䣬����preparedstatement��ʵ��
            String Sql = "insert into customers (name,email,birth)values(?,?,?)";//ռλ��

            ps = conn.prepareStatement(Sql);
            //���ռλ��
            ps.setString(1,"����ϼ");
            ps.setString(2,"Null.@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("2001-03-28");
            //ps.setDate(3, (java.sql.Date) new Date(date.getTime()));//sql�ҵ�date
           ps.setDate(3,  new java.sql.Date(date.getTime()));//
            //ִ��sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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

    }
}
