package com.Jdbc.querytest;

import java.util.Date;

/**
 * @ClassName: Customers
 * @Description:
 * @Author : MNNull
 * @Date : 2022/10/3  22:24
 */

/*
 *ORM编程思想(object relationship mapping)
 *一个数据表对应Java一个类
 * 一条记录对应Java一个对象
 * 一个字段对应Java的一个属性
 */

public class Customers {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customers() {

    }

    public Customers(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
