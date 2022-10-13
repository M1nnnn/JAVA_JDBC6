package com.jdbc2.transaction;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TransacTest
 * @Description:考虑事务的增删改操作
 * @Author : MNNull
 * @Date : 2022/10/6  23:42
 */

public class TransacTest {
    @Test
    public void transaction() {
        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();
            //验证是否是自动提交
            System.out.println(conn.getAutoCommit());

            //取消自动提交
            conn.setAutoCommit(false);

            String sql = "update user_table set balance = balance-100 where user = ?";
            update2(conn, sql, "AA");

            //模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance+100 where user = ?";
            update2(conn, sql2, "BB");

            System.out.println("转账成功");

            //提交数据
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //报异常，回滚数据
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {

            JDBC_connection.clossResource(conn, null);
        }


    }

    //通用的更新操作（version2.0）
    public static int update2(Connection conn, String sql, Object... args) {
//        Connection conn = null;
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

//=============事务操作=-==========
    @Test
    public void  transactionSelect() throws Exception {
        Connection conn = JDBC_connection.getConnection();
        //查看当前连接的隔离级别
        System.out.println(conn.getTransactionIsolation());

        //设置数据库的隔离级别
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);


        //取消自动提交
        conn.setAutoCommit(false);

        String sql = "select user , password,balance from user_table where user = ?";

        List<User> us = getForList2(conn, User.class, sql, "AA");

        System.out.println(us);

       // JDBC_connection.clossResource(conn,null);
    }
    @Test
    public void  transactionUpdate() throws Exception {
        Connection conn = JDBC_connection.getConnection();

        //取消自动提交
        conn.setAutoCommit(false);

        String sql = "update user_table set balance = ? where user = ?";

        update2(conn,sql,5000, "CC");

        Thread.sleep(1500);

        System.out.println("修改结束");

    }

    //通用的查询操作（version2.0）考虑上事务
    public static <T> List<T> getForList2(Connection conn,Class<T> clazz, String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
//            conn = JDBC_connection.getConnection();

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
}
