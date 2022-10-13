package com.Jdbc.Mysql_operate;

import com.Jdbc.Util.JDBC_connection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName: UpdateOperate
 * @Description:通用的增删改操作
 * @Author : MNNull
 * @Date : 2022/10/3  19:20
 */

public class UpdateOperate {

    public  static int update(String sql,Object ...args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            conn = JDBC_connection.getConnection();
            //预编译sql语句，获取prepared statement实例
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0 ; i< args.length ; i++){
                ps.setObject(i+1, args[i]);
            }
            //执行sql语句
            //ps.execute();
            /*
             * 功能描述: <br>
             *为了直观看到执行增删改操作是否成功，进行修改使用executeUpdate(),可以返回执行操作的行数
             */
           return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBC_connection.clossResource(conn,ps);
        }

        return 0;

    }
}
