package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @ClassName: CustomersOperate
 * @Description:���Customers����е�ͨ�ò�ѯ����
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
