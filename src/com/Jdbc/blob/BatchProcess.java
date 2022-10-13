package com.Jdbc.blob;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: BatchProce
 * @Description:用preparestatement实现批量处理
 * 批量插入
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

            System.out.println("花费的时间为：" + (time2-time0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.clossResource(conn,ps);
        }


    }
    
    @Test
    /*
     * 功能描述: <br>
     * 〈〉* 修改2：mysql服务器默认是关闭批处理的，我们需要通过一个参数，让mysql开启批处理的支持。
     *      ?rewriteBatchedStatements=true 写在配置文件的url后面
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

                //攒SQL
                ps.addBatch();

                if(i % 500 == 0){
                    //执行batch
                    ps.executeBatch();

                    //清空batch
                    ps.clearBatch();
                }


            }

            long time2 = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (time2-time0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.clossResource(conn,ps);
        }
    }

    //最总优化结果
    @Test
    public void insertBetchFinall(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBC_connection.getConnection();
            //不允许自动提交数据
            conn.setAutoCommit(false);

            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);

            long time0 = System.currentTimeMillis();

            for(int i = 1 ; i<=200000; i++){
                ps.setObject(1,"name_"+i);

                //攒SQL
                ps.addBatch();

                if(i % 500 == 0){
                    //执行batch
                    ps.executeBatch();

                    //清空batch
                    ps.clearBatch();
                }


            }

            //统一的提交数据
            conn.commit();

            long time2 = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (time2-time0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBC_connection.clossResource(conn,ps);
        }
    }
}
