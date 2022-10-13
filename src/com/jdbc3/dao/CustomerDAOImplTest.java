package com.jdbc3.dao;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;
import com.jdbc4.Connection.DBCPTest;
import com.jdbc4.Connection.DruidTestt;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOImplTest {

    CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void insert()  {
        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();

            Customers cust = new Customers(1,"林青霞","lin@126.com",new Date(1999-04-29));

            dao.insert(conn,cust);

            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }



    }

    @Test
    public void deleteById() {
        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();


            dao.deleteById(conn,21);

            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }
    }

    @Test
    public void update() {
        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();

            Customers cust = new Customers(25,"白梦妍","bai@qq.com",new Date(2000-1900,6-1,29));

            dao.update(conn,cust);



            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }
    }

    @Test
    public void getCustomerById() {

        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();


            Customers customerById = dao.getCustomerById(conn, 10);

            System.out.println(customerById);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }
    }

    @Test
    public void getAll() {

        Connection conn = null;
        try {
            conn = JDBC_connection.getConnection();


            List<Customers> all = dao.getAll(conn);
            all.forEach(System.out::println);

            //System.out.println(all);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }
    }

    @Test
    public void getCount() {

        Connection conn = null;
        try {
           // conn = JDBC_connection.getConnection();
            //conn = DBCPTest.getDbcpConn();
            conn = DruidTestt.getDruidConn();

            Long count = dao.getCount(conn);

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }
    }

    @Test
    public void getMaxBirth() {

        Connection conn = null;
        try {
           // conn = JDBC_connection.getConnection();
            conn = DBCPTest.getDbcpConn();

            java.sql.Date maxBirth = (Date) dao.getMaxBirth(conn);

            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn,null);
        }
    }
}