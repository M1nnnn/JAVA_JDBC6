package com.jdbc3.dao;

import com.Jdbc.querytest.Customers;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * ��������: <br>
 * �����˽ӿ����ڹ淶customers��ĳ��ò���
 * @Param: 
 * @Return: 
 * @Author: itestar
 * @Date: 2022/10/9 21:20
 */

public interface CustomerDAO {
/**
 * ��������: <br>
 * ������Customers������ӵ����ݿ���
 * @Param: [conn, cust]
 * @Return: [java.sql.Connection, com.Jdbc.querytest.Customers]
 * @Author: itestar
 * @Date: 2022/10/9 21:24
 */
    void insert(Connection conn, Customers cust);
    
    /**
     * ��������: <br>
     * �������Id�������ݿ�
     * @Param: 
     * @Return: 
     * @Author: itestar
     * @Date: 2022/10/9 21:25
     */
    
    void deleteById(Connection conn,int Id);

    /*
     * ��������: <br>
     * �������Cust�����޸����ݿ�ָ������
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/9 21:28
     */

    void update(Connection conn,Customers cust);
/*
 * ��������: <br>
 * ��������ָ��ID��ѯcustomers����
 * @Param:
 * @Return:
 * @Author: itestar
 * @Date: 2022/10/9 21:33
 */

    Customers getCustomerById(Connection conn,int id);

/*
 * ��������: <br>
 * ������ѯCustomers��������в������
 * @Param:
 * @Return:
 * @Author: itestar
 * @Date: 2022/10/9 21:34
 */
    List<Customers> getAll (Connection conn);

    /*
     * ��������: <br>
     * �����������ݿ��е���Ŀ��
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/9 21:37
     */

    Long getCount(Connection conn);
    
    /*
     * ��������: <br>
     * �����������ݿ�����������
     * @Param: 
     * @Return: 
     * @Author: itestar
     * @Date: 2022/10/9 21:38
     */
    Date getMaxBirth(Connection conn);
}
