package com.Jdbc.querytest;

import java.util.Date;

/**
 * @ClassName: Order
 * @Description:
 * @Author : MNNull
 * @Date : 2022/10/4  20:06
 */

public class Order {
    private int  orderId;
    private String orderName;
    private Date orderBirth;

    public Order() {
    }

    public Order(int orderId, String orderName, Date orderBirth) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderBirth = orderBirth;
    }



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderBirth() {
        return orderBirth;
    }

    public void setOrderBirth(Date orderBirth) {
        this.orderBirth = orderBirth;
    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderBirth='" + orderBirth + '\'' +
                '}';
    }
}
