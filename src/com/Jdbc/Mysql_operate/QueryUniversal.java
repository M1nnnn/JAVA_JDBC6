package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;
import com.Jdbc.querytest.Order;
import org.junit.Test;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: QueryUniversal
 * @Description:使用preparedstatement实现对不同表的查询操作
 * @Author : MNNull
 * @Date : 2022/10/4  21:54
 */

public class QueryUniversal {

    @Test
    public void TestGetForList(){
        String sql = "select id , name, email ,birth from customers where id < ?";
        List<Customers> list = getForList(Customers.class, sql, 3);
      //System.out.println(list);
        //遍历集合
        list.forEach(System.out::println);
    }
/**
 * 功能描述: <br>
 * 〈〉实现多条数据的查询
 * @Param: [clazz, sql, args]
 * @Return: [java.lang.Class<T>, java.lang.String, java.lang.Object...]
 * @Author: itestar
 * @Date: 2022/10/4 23:11
 */

    public static  <T>  List<T> getForList(Class<T> clazz, String sql, Object... args){
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

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

            //创建集合对象
            ArrayList<T> tArrayList = new ArrayList<>();

            while (resultSet.next()) {
                T t = clazz.newInstance();
                //处理每一行数据的每一个列，给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    //获取列名
                    //String columnName = metaData.getColumnName(i + 1);
//                  获取列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);

                }
                     tArrayList.add(t);
                }
                    return tArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.closeCourse(conn, ps, resultSet);
        }

        return null;
    }

    @Test
    public void queryTest() {
        String sql = "select id,name,birth from customers where id = ?";

        Object instance = getInstance(Customers.class, sql, 1);

        System.out.println(instance);
    }


    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
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

            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

            if (resultSet.next()) {
                T t = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    //获取列名
                    //String columnName = metaData.getColumnName(i + 1);
//                  获取列的别名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);

                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);

                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.closeCourse(conn, ps, resultSet);
        }

        return null;
    }
}
