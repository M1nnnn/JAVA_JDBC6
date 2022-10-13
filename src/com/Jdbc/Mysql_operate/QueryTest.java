package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;
import com.Jdbc.querytest.Order;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName: QueryTest
 * @Description:���Customers����еĲ�ѯ����//Ҫ��ѯ�ĸ�������ֶβ�һ��
 * @Author : MNNull
 * @Date : 2022/10/3  21:24
 */

public class QueryTest {

    @Test
    /**
     * ����Order��Customers���ͨ�÷���ʵ�ֵ������ݲ�ѯ
     */
    public void queryForOrder(){
        String sql = "select order_id orderId,order_name orderName from `order` where order_id = ? ";
        Order order = OrderOperate.orderQuery(sql, 1);
        System.out.println(order);

    }

    @Test
    public void queryForCustomers(){
        String sql = "Select id,name,email from customers where id = ?";
        Customers customers = CustomersOperate.queryCustomers(sql, 2);
        System.out.println(customers);
    }


    @Test
    public void toQuery()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //�������ݿ�
            conn = JDBC_connection.getConnection();
            //Ԥ����sql���
            String sql = "Select id, name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);

            //���ռλ��
            ps.setObject(1, 5);
            //ִ��sql��䣬���ؽ����
            resultSet = ps.executeQuery();
            //��������
            if (resultSet.next()) {//next:�ж���һ��������Ƿ������ݣ�������򷵻�true����ָ������

                //��ȡ�������ݵĸ����ֶ�ֵ
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                //������
                //��ʽһ
                //          System.out.println("id = "+ id + ",name = "+ name +"email = "+ email +"birth =" +birth);
                //��ʽ��
                //            Object[] data = new Object[]{id,name,email,birth};
                //            System.out.println(data);
                //�����ݷ�װΪ����
                Customers customers = new Customers(id, name, email, birth);
                System.out.println(customers.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBC_connection.closeCourse(conn, ps, resultSet);
        }

    }
            
}
