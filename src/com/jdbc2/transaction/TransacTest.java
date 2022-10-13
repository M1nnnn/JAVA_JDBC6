package com.jdbc2.transaction;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TransacTest
 * @Description:�����������ɾ�Ĳ���
 * @Author : MNNull
 * @Date : 2022/10/6  23:42
 */

public class TransacTest {
    @Test
    public void transaction() {
        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();
            //��֤�Ƿ����Զ��ύ
            System.out.println(conn.getAutoCommit());

            //ȡ���Զ��ύ
            conn.setAutoCommit(false);

            String sql = "update user_table set balance = balance-100 where user = ?";
            update2(conn, sql, "AA");

            //ģ�������쳣
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance+100 where user = ?";
            update2(conn, sql2, "BB");

            System.out.println("ת�˳ɹ�");

            //�ύ����
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //���쳣���ع�����
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {

            JDBC_connection.clossResource(conn, null);
        }


    }

    //ͨ�õĸ��²�����version2.0��
    public static int update2(Connection conn, String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //��ȡ���ݿ�����
            // conn = JDBC_connection.getConnection();
            //Ԥ����sql��䣬��ȡprepared statementʵ��
            ps = conn.prepareStatement(sql);
            //���ռλ��
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //ִ��sql���
            //ps.execute();
            /*
             * ��������: <br>
             *Ϊ��ֱ�ۿ���ִ����ɾ�Ĳ����Ƿ�ɹ��������޸�ʹ��executeUpdate(),���Է���ִ�в���������
             */
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBC_connection.clossResource(null, ps);
        }

        return 0;
    }

//=============�������=-==========
    @Test
    public void  transactionSelect() throws Exception {
        Connection conn = JDBC_connection.getConnection();
        //�鿴��ǰ���ӵĸ��뼶��
        System.out.println(conn.getTransactionIsolation());

        //�������ݿ�ĸ��뼶��
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);


        //ȡ���Զ��ύ
        conn.setAutoCommit(false);

        String sql = "select user , password,balance from user_table where user = ?";

        List<User> us = getForList2(conn, User.class, sql, "AA");

        System.out.println(us);

       // JDBC_connection.clossResource(conn,null);
    }
    @Test
    public void  transactionUpdate() throws Exception {
        Connection conn = JDBC_connection.getConnection();

        //ȡ���Զ��ύ
        conn.setAutoCommit(false);

        String sql = "update user_table set balance = ? where user = ?";

        update2(conn,sql,5000, "CC");

        Thread.sleep(1500);

        System.out.println("�޸Ľ���");

    }

    //ͨ�õĲ�ѯ������version2.0������������
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
            JDBC_connection.closeCourse(null, ps, resultSet);
        }

        return null;
    }
}
