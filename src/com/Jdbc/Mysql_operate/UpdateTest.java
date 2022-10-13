package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * @Descriped:���淽���������ݿ�customers���е�һ�����ݾݽ����޸�
 * @ClassName: Update_Test
 * @Author : MNNull
 * @Date : 2022/10/2  16:19
 */

public class UpdateTest {
        @Test
    public void test_update()  {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                //1.��ȡ���ݿ�����
                conn = JDBC_connection.getConnection();

                //2.����sql��䣬��ȡPrepared statementʵ��
                String sql = "update customers set name = ? where id = ?";
                ps = conn.prepareStatement(sql);

                //3.���ռλ��
                ps.setObject(1,"³Ѹ");
                ps.setObject(2,18);
                //4.ִ��sql
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //5.�ͷ���Դ��
                JDBC_connection.clossResource(conn,ps);
            }



        }
}
