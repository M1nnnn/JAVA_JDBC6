package com.Jdbc.preparestatement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Connection_final {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

    //�������ӷ����������ݿ����ӵ��ĸ������������������ļ���
   // public void Connection_test2() throws Exception{
//      ��ȡ�����ļ����ĸ�������Ϣ
        InputStream is = Connection_final.class.getClassLoader().getResourceAsStream("jdbc.properties");

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

    }
}
