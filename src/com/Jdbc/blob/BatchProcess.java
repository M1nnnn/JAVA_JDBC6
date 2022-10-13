package com.Jdbc.blob;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: BatchProce
 * @Description:��preparestatementʵ����������
 * ��������
 * @Author : MNNull
 * @Date : 2022/10/6  20:31
 */

public class BatchProcess {
    @Test
    public void  batchInsert()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBC_connection.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            long time0 = System.currentTimeMillis();

            for(int i = 1 ; i<20000; i++){
                ps.setObject(1,"name_"+i);
                ps.execute();
            }

            long time2 = System.currentTimeMillis();

            System.out.println("���ѵ�ʱ��Ϊ��" + (time2-time0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.clossResource(conn,ps);
        }


    }
    
    @Test
    /*
     * ��������: <br>
     * ����* �޸�2��mysql������Ĭ���ǹر�������ģ�������Ҫͨ��һ����������mysql�����������֧�֡�
     *      ?rewriteBatchedStatements=true д�������ļ���url����
     * @Param: []
     * @Return: []
     * @Author: itestar
     * @Date: 2022/10/6 22:50
     */
    
    public void insertBetch(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBC_connection.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            long time0 = System.currentTimeMillis();

            for(int i = 1 ; i<20000; i++){
                ps.setObject(1,"name_"+i);

                //��SQL
                ps.addBatch();

                if(i % 500 == 0){
                    //ִ��batch
                    ps.executeBatch();

                    //���batch
                    ps.clearBatch();
                }


            }

            long time2 = System.currentTimeMillis();

            System.out.println("���ѵ�ʱ��Ϊ��" + (time2-time0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.clossResource(conn,ps);
        }
    }

    //�����Ż����
    @Test
    public void insertBetchFinall(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBC_connection.getConnection();
            //�������Զ��ύ����
            conn.setAutoCommit(false);

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            long time0 = System.currentTimeMillis();

            for(int i = 1 ; i<=200000; i++){
                ps.setObject(1,"name_"+i);

                //��SQL
                ps.addBatch();

                if(i % 500 == 0){
                    //ִ��batch
                    ps.executeBatch();

                    //���batch
                    ps.clearBatch();
                }


            }

            //ͳһ���ύ����
            conn.commit();

            long time2 = System.currentTimeMillis();

            System.out.println("���ѵ�ʱ��Ϊ��" + (time2-time0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.clossResource(conn,ps);
        }
    }
}
