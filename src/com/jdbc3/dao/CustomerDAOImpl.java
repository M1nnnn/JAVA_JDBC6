package com.jdbc3.dao;

import com.Jdbc.querytest.Customers;

import java.sql.Connection;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: CustomerDAOImpl
 * @Description:
 * @Author : MNNull
 * @Date : 2022/10/9  21:40
 */

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO{

    @Override
    public void insert(Connection conn, Customers cust) {
        String sql = "insert into customers(name,email,birth) values(?, ?, ?)";
        updateBAO(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int Id) {
        String sql = "delete from customers where Id = ?";
        updateBAO(conn,sql,Id);
    }

    @Override
    public void update(Connection conn, Customers cust) {
        String sql = "update customers set name = ? , email = ? ,birth = ? where id = ?";
        updateBAO(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public Customers getCustomerById(Connection conn, int id) {
        String sql = "select id , name ,email ,birth from customers where id = ?";
        Customers customers = queryCustomers(conn, sql, id);
        return customers;
    }

    @Override
    public List<Customers> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        List<Customers> forListBao = getForListBao(conn, Customers.class, sql);
        return forListBao;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        Long value = getValue(conn, sql);
        return  value;
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        Object value = getValue(conn, sql);
        return (Date) value;
    }
}
