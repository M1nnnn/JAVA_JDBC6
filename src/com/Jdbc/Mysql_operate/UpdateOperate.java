package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: UpdateOperate
 * @Description:ͨ�õ���ɾ�Ĳ���
 * @Author : MNNull
 * @Date : 2022/10/3  19:20
 */

public class UpdateOperate {

    public  static int update(String sql,Object ...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //��ȡ���ݿ�����
            conn = JDBC_connection.getConnection();
            //Ԥ����sql��䣬��ȡprepared statementʵ��
            ps = conn.prepareStatement(sql);
            //���ռλ��
            for(int i = 0 ; i< args.length ; i++){
                ps.setObject(i+1, args[i]);
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
            JDBC_connection.clossResource(conn,ps);
        }

        return 0;

    }
}
