package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: UpdateDelete
 * @Description:调用通用的增删改操作进行删除和增加
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
            //获取数据库连接
            conn = JDBC_connection.getConnection();
            //编译sql语句，获取prepared statement实例
            String sql = "Delete from customers where id = 18";//应该使用占位符的
            ps = conn.prepareStatement(sql);
            //执行sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            JDBC_connection.clossResource(conn, ps);
        }

    }*/
}
