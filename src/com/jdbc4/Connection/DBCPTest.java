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
 * @Description:测试dbcp的数据库连接池
 * @Author : MNNull
 * @Date : 2022/10/10  18:46
 */

public class DBCPTest {
    @Test
    public void getConnection() throws SQLException {
        //创建dpcp数据库连接池
        BasicDataSource source = new BasicDataSource();
//      设置基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/jdbc_test");
        source.setUsername("root");
        source.setPassword("abcd4567");

        //还可以设置数据库连接池管理的相关操作
        source.setInitialSize(10);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }



//方式二,shi用配置文件

    private static DataSource dataSource;

    static {

        try {
            Properties pros = new Properties();

            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            pros.load(is);

            //创建dbcp数据库连接池
            dataSource = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getDbcpConn() throws SQLException {
    /*    Properties pros = new Properties();
        //方式一
        // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");

        //方式二
        FileInputStream is  = new FileInputStream(new File("src/dbcp.properties"));
        pros.load(is);
        //创建dbcp数据库连接池
        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(pros);
*/
        Connection conn = dataSource.getConnection();
        return conn;

    }

}
