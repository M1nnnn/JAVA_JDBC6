package com.jdbc3.dao;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseDAO
 * @Description:��װ�˶����ݱ��ͨ�ò���
 * @Author : MNNull
 * @Date : 2022/10/8  23:41
 */

public class BaseDAO {

    //ͨ�õ���ɾ�Ĳ�������������
    public static int updateBAO(Connection conn, String sql, Object... args) {
        //Connection conn = null;
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

    //ͨ�õĲ�ѯ������������
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

    //���ڲ�ѯ����ֵ�ķ���
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

    //ͨ�õ�Customer��ѯ����
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
            //��ȡ�����Ԫ����
            ResultSetMetaData metaData = resultSet.getMetaData();
//ͨ��getMetaData��ȡ�����������
            int columnCount = metaData.getColumnCount();


            if (resultSet.next()) {
                Customers customers = new Customers();
                //����������һ�����ݵ�ÿһ��
                for (int i = 0; i < columnCount; i++) {
                    //ͨ��forѭ����ȡ�е�ֵ
                    Object columnValue = resultSet.getObject(i + 1);
                    //ͨ��getMetaData��ȡ�����������
                    String columnName = metaData.getColumnName(i+1);
                    //��Customers��ָ������column Name����ָ��ֵcolumnValue
                    //ͨ��Java����
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
