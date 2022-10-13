package com.jdbc4.Connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName: DBCPTest
 * @Description:����dbcp�����ݿ����ӳ�
 * @Author : MNNull
 * @Date : 2022/10/10  18:46
 */

public class DBCPTest {
    @Test
    public void getConnection() throws SQLException {
        //����dpcp���ݿ����ӳ�
        BasicDataSource source = new BasicDataSource();
//      ���û�����Ϣ
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/jdbc_test");
        source.setUsername("root");
        source.setPassword("abcd4567");

        //�������������ݿ����ӳع������ز���
        source.setInitialSize(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }



//��ʽ��,shi�������ļ�

    private static DataSource dataSource;

    static {

        try {
            Properties pros = new Properties();

            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            pros.load(is);

            //����dbcp���ݿ����ӳ�
            dataSource = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getDbcpConn() throws SQLException {
    /*    Properties pros = new Properties();
        //��ʽһ
        // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");

        //��ʽ��
        FileInputStream is  = new FileInputStream(new File("src/dbcp.properties"));
        pros.load(is);
        //����dbcp���ݿ����ӳ�
        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(pros);
*/
        Connection conn = dataSource.getConnection();
        return conn;

    }

}
