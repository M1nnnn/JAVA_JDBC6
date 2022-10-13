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
 * @Description:ʹ��preparedstatementʵ�ֶԲ�ͬ��Ĳ�ѯ����
 * @Author : MNNull
 * @Date : 2022/10/4  21:54
 */

public class QueryUniversal {

    @Test
    public void TestGetForList(){
        String sql = "select id , name, email ,birth from customers where id < ?";
        List<Customers> list = getForList(Customers.class, sql, 3);
      //System.out.println(list);
        //��������
        list.forEach(System.out::println);
    }
/**
 * ��������: <br>
 * ����ʵ�ֶ������ݵĲ�ѯ
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

            //�������϶���
            ArrayList<T> tArrayList = new ArrayList<>();

            while (resultSet.next()) {
                T t = clazz.newInstance();
                //����ÿһ�����ݵ�ÿһ���У���t����ָ�������Ը�ֵ
                for (int i = 0; i < columnCount; i++) {
                    //��ȡ����
                    //String columnName = metaData.getColumnName(i + 1);
//                  ��ȡ�еı���
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
                    //��ȡ����
                    //String columnName = metaData.getColumnName(i + 1);
//                  ��ȡ�еı���
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
