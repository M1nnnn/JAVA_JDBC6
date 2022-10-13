package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @ClassName: CustomersOperate
 * @Description:针对Customers表进行的通用查询操作
 * @Author : MNNull
 * @Date : 2022/10/4  17:44
 */

public class CustomersOperate {
    public static Customers queryCustomers(String sql, Object... args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBC_connection.getConnection();

             ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

             resultSet = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
//通过getMetaData获取结果集的列数
            int columnCount = metaData.getColumnCount();


            if (resultSet.next()) {
                Customers customers = new Customers();
                //处理结果集中一行数据的每一列
                for (int i = 0; i < columnCount; i++) {
                    //通过for循环获取列的值
                    Object columnValue = resultSet.getObject(i + 1);
                //通过getMetaData获取结果集的列名
                    String columnName = metaData.getColumnName(i+1);
                    //给Customers的指定属性column Name赋予指定值columnValue
                    //通过Java反射
                    Field field = Customers.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customers,columnValue);

                }
                return customers;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.closeCourse(null,ps,resultSet);
        }

        return null;
    }
}
