package com.jdbc4.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: ConnectionTest
 * @Description:数据库连接池c3p0
 * @Author : MNNull
 * @Date : 2022/10/10  13:11
 */

public class C3P0Test {
    //方式一：
    @Test
    public void getConnection() throws PropertyVetoException, SQLException {
        //获取c3p0数据库连接池的配置信息
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/jdbc_test" );
        cpds.setUser("root");
        cpds.setPassword("abcd4567");
        //通过相应的参数对数据库连接池进行相应的管理
        //设置数据库连接池的连接数
        cpds.setInitialPoolSize(10);

        Connection conn = cpds.getConnection();

        System.out.println(conn);

//      销毁数据库连接池,一般不用
        DataSources.destroy(cpds);
    }
    //方式二，配置文件
    @Test
    public void getConnectionC3P0() throws SQLException {

        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");

        Connection conn = cpds.getConnection();

        System.out.println(conn);
    }





}
