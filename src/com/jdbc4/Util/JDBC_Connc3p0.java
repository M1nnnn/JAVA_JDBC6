package com.jdbc4.Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: JDBC_Conn
 * @Description:
 * @Author : MNNull
 * @Date : 2022/10/10  18:26
 */

public class JDBC_Connc3p0 {

    static private ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    public static Connection JDBC_Connection() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }
}
