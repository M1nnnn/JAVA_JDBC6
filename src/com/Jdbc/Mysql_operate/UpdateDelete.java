package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: UpdateDelete
 * @Description:����ͨ�õ���ɾ�Ĳ�������ɾ��������
 * @Author : MNNull
 * @Date : 2022/10/3  15:07
 */

public class UpdateDelete {
    @Test
    public void DeleteCustomers2(){
        String sql = "Delete from  customers where id = ?";
        UpdateOperate.update(sql,19);
    }

    @Test
    public void Add(){
        String sql = "Update `order` set order_name = ? where order_id = ?";
        UpdateOperate.update(sql,"DD" , "2");
    }

/*    public void DeleteCustomers()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //��ȡ���ݿ�����
            conn = JDBC_connection.getConnection();
            //����sql��䣬��ȡprepared statementʵ��
            String sql = "Delete from customers where id = 18";//Ӧ��ʹ��ռλ����
            ps = conn.prepareStatement(sql);
            //ִ��sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ͷ���Դ
            JDBC_connection.clossResource(conn, ps);
        }

    }*/
}
