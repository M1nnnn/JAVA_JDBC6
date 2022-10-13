package com.Jdbc.blob;

import com.Jdbc.Util.JDBC_connection;
import com.Jdbc.querytest.Customers;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @ClassName: BlobTest
 * @Description:��preparestatement����blob���͵�����
 * @Author : MNNull
 * @Date : 2022/10/6  16:11
 */

public class BlobTest {
    @Test
    //�����ݿ��в���blob��������
    public void insertBlob() throws Exception {
        Connection conn = JDBC_connection.getConnection();
        String sql = "insert into customers (name,email,birth,photo) values(? ,?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, "��ΰ");
        ps.setObject(2, "chen123@qq.com");
        ps.setObject(3, "1999-02-22");

        FileInputStream is = new FileInputStream(new File("girl.jpg"));
        ps.setBlob(4, is);

        ps.execute();

        JDBC_connection.clossResource(conn, ps);
    }

    @Test
    //��ѯ���ݿ��е�blob��������
    public void queryBlob() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBC_connection.getConnection();

            String sql = "select id, name,email,birth,photo from customers where id = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, 23);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                Customers customers = new Customers(id, name, email, birth);
                System.out.println(customers);

                //��blob���͵��ֶ��������������뱾��
                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();

                fos = new FileOutputStream("chen.jpg");

                byte[] buffer = new byte[1024];
                int len;

                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            JDBC_connection.closeCourse(conn, ps, resultSet);
        }


    }
}
