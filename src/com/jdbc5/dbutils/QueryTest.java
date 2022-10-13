package com.jdbc5.dbutils;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;
import com.jdbc4.Connection.DruidTestt;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: QueryTest
 * @Description:commen_dbutils���ṩ�Ŀ�Դ�����࣬��װ�������ɾ�ĵĲ���
 * @Author : MNNull
 * @Date : 2022/10/10  23:18
 */

public class QueryTest {
    //���Բ���
    @Test
    public void updateRunner() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();

            conn = DruidTestt.getDruidConn();
            String sql = "insert into customers(name,email,birth) values(?,?,?) ";

            int update = runner.update(conn, sql, "����", "yang@qq.com", "1999-08-23");

            if (update != 0) {
                System.out.println("����" + update + "�����ݱ��޸�");
            } else System.out.println("�޸�ʧ��");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn, null);
        }
    }
//���Բ�ѯ

    /**
     * ��������: <br>
     * ����
     * BeanHandler��ResultSetHandle�ӿڵ�ʵ����,���ڷ�װ���е�һ����¼�����ص�����¼
     *
     * @Param: []
     * @Return: []
     * @Author: itestar
     * @Date: 2022/10/11 22:23
     */

    @Test
    public void QueryTest1() {
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            conn = DruidTestt.getDruidConn();

            String sql = "select id,email,birth from customers where id = ?";

            BeanHandler beanHandler = new BeanHandler<>(Customers.class);

            Object query = queryRunner.query(conn, sql, beanHandler, 22);

            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn, null);
        }

    }


    /*
     * ��������: <br>
     * ����BeanListHandlerҲ��ʵ���࣬���ڷ�װ��������
     * @Param: []
     * @Return: []
     * @Author: itestar
     * @Date: 2022/10/11 22:29
     */
    @Test
    public void QueryTest2() {
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            conn = DruidTestt.getDruidConn();

            String sql = "select id,name,email,birth from customers where id < ?";

            BeanListHandler<Customers> handler = new BeanListHandler<>(Customers.class);


            List<Customers> query = queryRunner.query(conn, sql, handler, 22);

            query.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn, null);
        }
    }

    /*
     * ��������: <br>
     * ���������ѯScalarHandler
     * @Param: []
     * @Return: []
     * @Author: itestar
     * @Date: 2022/10/11 22:43
     */
    @Test
    public void QueryTest3() {
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            conn = DruidTestt.getDruidConn();

            String sql = "select count(*) from customers";

            ScalarHandler handler = new ScalarHandler();

            Long query = (Long) queryRunner.query(conn, sql, handler);

            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn, null);
        }

    }

    @Test
    public void QueryTest4() {
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            conn = DruidTestt.getDruidConn();

            String sql = "select max(birth) from customers";

            ScalarHandler handler = new ScalarHandler();

            Date query = (Date) queryRunner.query(conn, sql, handler);

            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn, null);
        }

    }

    /*
     * ��������: <br>
     * �����Զ���ResultSetHandle��ʵ����
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/11 22:58
     */
    @Test
    public void QueryTest5() {
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();

            conn = DruidTestt.getDruidConn();

            String sql = "select id,name,email,birth from customers where id = ?";

            ResultSetHandler<Customers> handler = new ResultSetHandler<Customers>() {

                public Customers handle(ResultSet resultSet) throws SQLException {
                    //  return null;
                    if (resultSet.next()) {
                        int id = resultSet.getInt("Id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        Date birth = resultSet.getDate("birth");
                        Customers customers = new Customers(id, name, email, birth);
                        return customers;
                    }
                    return null;
                }
            };

            Customers quer = queryRunner.query(conn, sql, handler, 2);

            System.out.println(quer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_connection.clossResource(conn, null);
        }
    }

}
