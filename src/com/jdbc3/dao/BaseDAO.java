package com.jdbc3.dao;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseDAO
 * @Description:封装了对数据表的通用操作
 * @Author : MNNull
 * @Date : 2022/10/8  23:41
 */

public class BaseDAO {

    //通用的增删改操作（考虑事务）
    public static int updateBAO(Connection conn, String sql, Object... args) {
        //Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            // conn = JDBC_connection.getConnection();
            //预编译sql语句，获取prepared statement实例
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //执行sql语句
            //ps.execute();
            /*
             * 功能描述: <br>
             *为了直观看到执行增删改操作是否成功，进行修改使用executeUpdate(),可以返回执行操作的行数
             */
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBC_connection.clossResource(null, ps);
        }

        return 0;

    }

    //通用的查询操作考虑事务
    public static <T> List<T> getForListBao(Connection conn, Class<T> clazz, String sql, Object... args) {
        // Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //conn = JDBC_connection.getConnection();

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
            JDBC_connection.closeCourse(null, ps, resultSet);
        }

        return null;
    }

    //用于查询特殊值的方法
    public <E> E getValue(Connection conn,String sql , Object...args) {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return (E) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.closeCourse(null, ps, resultSet);
        }

        return null;
    }

    //通用的Customer查询操作
    public static Customers queryCustomers(Connection conn, String sql, Object... args)  {
        // Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //conn = JDBC_connection.getConnection();

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
