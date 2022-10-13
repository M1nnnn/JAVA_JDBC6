package com.jdbc4.Connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName: DruidTestt
 * @Description:测试druid数据库连接池
 * @Author : MNNull
 * @Date : 2022/10/10  21:37
 */

public class DruidTestt {

    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getDruidConn() throws Exception {


        Connection conn = dataSource.getConnection();

        return conn;
//        System.out.println(conn);

    }
}
