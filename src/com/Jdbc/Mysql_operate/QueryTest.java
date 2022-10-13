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
 * @Description:针对Customers表进行的查询操作//要查询的各个表的字段不一样
 * @Author : MNNull
 * @Date : 2022/10/3  21:24
 */

public class QueryTest {

    @Test
    /**
     * 调用Order和Customers表的通用方法实现单行数据查询
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
            //连接数据库
            conn = JDBC_connection.getConnection();
            //预编译sql语句
            String sql = "Select id, name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);

            //填充占位符
            ps.setObject(1, 5);
            //执行sql语句，返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            if (resultSet.next()) {//next:判断下一个结果集是否有数据，如果有则返回true，并指针下移

                //获取这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                //输出结果
                //方式一
                //          System.out.println("id = "+ id + ",name = "+ name +"email = "+ email +"birth =" +birth);
                //方式二
                //            Object[] data = new Object[]{id,name,email,birth};
                //            System.out.println(data);
                //将数据封装为对象
                Customers customers = new Customers(id, name, email, birth);
                System.out.println(customers.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBC_connection.closeCourse(conn, ps, resultSet);
        }

    }
            
}
