package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * @Descriped:常规方法：对数据库customers表中的一条数据据进行修改
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
                //1.获取数据库连接
                conn = JDBC_connection.getConnection();

                //2.编译sql语句，获取Prepared statement实例
                String sql = "update customers set name = ? where id = ?";
                ps = conn.prepareStatement(sql);

                //3.填充占位符
                ps.setObject(1,"鲁迅");
                ps.setObject(2,18);
                //4.执行sql
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //5.释放资源‘
                JDBC_connection.clossResource(conn,ps);
            }



        }
}
