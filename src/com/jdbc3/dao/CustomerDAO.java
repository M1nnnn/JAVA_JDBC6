package com.jdbc3.dao;

import com.Jdbc.querytest.Customers;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * 功能描述: <br>
 * 〈〉此接口用于规范customers表的常用操作
 * @Param: 
 * @Return: 
 * @Author: itestar
 * @Date: 2022/10/9 21:20
 */

public interface CustomerDAO {
/**
 * 功能描述: <br>
 * 〈〉将Customers对象添加到数据库中
 * @Param: [conn, cust]
 * @Return: [java.sql.Connection, com.Jdbc.querytest.Customers]
 * @Author: itestar
 * @Date: 2022/10/9 21:24
 */
    void insert(Connection conn, Customers cust);
    
    /**
     * 功能描述: <br>
     * 〈〉针对Id操作数据库
     * @Param: 
     * @Return: 
     * @Author: itestar
     * @Date: 2022/10/9 21:25
     */
    
    void deleteById(Connection conn,int Id);

    /*
     * 功能描述: <br>
     * 〈〉针对Cust对象，修改数据库指定对象
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/9 21:28
     */

    void update(Connection conn,Customers cust);
/*
 * 功能描述: <br>
 * 〈〉根据指定ID查询customers对象
 * @Param:
 * @Return:
 * @Author: itestar
 * @Date: 2022/10/9 21:33
 */

    Customers getCustomerById(Connection conn,int id);

/*
 * 功能描述: <br>
 * 〈〉查询Customers对象的所有操作结果
 * @Param:
 * @Return:
 * @Author: itestar
 * @Date: 2022/10/9 21:34
 */
    List<Customers> getAll (Connection conn);

    /*
     * 功能描述: <br>
     * 〈〉返回数据库中的条目数
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/9 21:37
     */

    Long getCount(Connection conn);
    
    /*
     * 功能描述: <br>
     * 〈〉返回数据库中最大的生日
     * @Param: 
     * @Return: 
     * @Author: itestar
     * @Date: 2022/10/9 21:38
     */
    Date getMaxBirth(Connection conn);
}
