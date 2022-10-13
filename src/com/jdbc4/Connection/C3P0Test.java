package com.jdbc4.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: ConnectionTest
 * @Description:���ݿ����ӳ�c3p0
 * @Author : MNNull
 * @Date : 2022/10/10  13:11
 */

public class C3P0Test {
    //��ʽһ��
    @Test
    public void getConnection() throws PropertyVetoException, SQLException {
        //��ȡc3p0���ݿ����ӳص�������Ϣ
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/jdbc_test" );
        cpds.setUser("root");
        cpds.setPassword("abcd4567");
        //ͨ����Ӧ�Ĳ��������ݿ����ӳؽ�����Ӧ�Ĺ���
        //�������ݿ����ӳص�������
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();

        System.out.println(conn);

//      �������ݿ����ӳ�,һ�㲻��
        DataSources.destroy(cpds);
    }
    //��ʽ���������ļ�
    @Test
    public void getConnectionC3P0() throws SQLException {

        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");

        Connection conn = cpds.getConnection();

        System.out.println(conn);
    }





}
