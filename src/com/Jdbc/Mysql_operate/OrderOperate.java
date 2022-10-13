package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;
import com.Jdbc.querytest.Order;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @ClassName: OrderOperate
 * @Description:针对Order表进行的通用查询操作
 * @Author : MNNull
 * @Date : 2022/10/4  19:29
 */

public class OrderOperate {
    public static Order orderQuery(String sql, Object...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBC_connection.getConnection();

            ps = conn.prepareStatement(sql);


            for (int i = 0; i < args.length; i++){
                ps.setObject(i+1, args[i]);
            }

            resultSet = ps.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

            if(resultSet.next()){
                Order order = new Order();


                for(int i = 0 ; i< columnCount; i++){
                    //获取列名
                    //String columnName = metaData.getColumnName(i + 1);
//                  获取列的别名
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Object columnValue = resultSet.getObject(i + 1);

                    Field declaredField = Order.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(order ,columnValue);

                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.closeCourse(conn,ps,resultSet);
        }

        return null;
    }


}
