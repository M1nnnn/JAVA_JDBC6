package com.Jdbc.exercisetest;

import com.Jdbc.Mysql_operate.QueryUniversal;
import com.Jdbc.Mysql_operate.UpdateOperate;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: exercise1
 * @Description:
 * @Author : MNNull
 * @Date : 2022/10/5  17:17
 */

public class exercise1 {
    @Test
    public void testInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("�������û�����");
        String name = sc.next();
        System.out.println("���������䣺");
        String email = sc.next();
        System.out.println("���������գ�");
        String birth = sc.next();

        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        int update = UpdateOperate.update(sql, name, email, birth);
        System.out.println(update + "�����ݱ��޸�");
        if (update > 0) {
            System.out.println("��ӳɹ�");
        } else
            System.out.println("���ʧ��");


    }

    @Test
    /**
     * ��������: <br>
     * �������� java �����������֤�Ż�׼��֤�ſ��Բ�ѯ��ѧ���Ļ�����Ϣ��
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/5 19:24
     */
    public void queryTest() {
        System.out.println("��������Ҫ��������ͣ�");
        System.out.println("a.׼��֤��" + "\n" + "b.���֤��");
        Scanner sc = new Scanner(System.in);
        String select = sc.next();

        if ("a".equalsIgnoreCase(select)) {
            System.out.println("������׼��֤�ţ�");
            Scanner exameId = new Scanner(System.in);
            String exame = exameId.next();

            String sql = "select flowId flowId, Type type,IdCard idCard,ExamCard examCard,StudentName studentName,location location,Grade grade from examstudent where examcard = ?";

            List<Student> studentList = QueryUniversal.getForList(Student.class, sql, exame);

            if(studentList.isEmpty()){
                System.out.println("�������׼��֤��֤������");
            }else{
            System.out.println(studentList);
            }

        } else if ("b".equalsIgnoreCase(select)) {
            System.out.println("���������֤�ţ�");
            Scanner IdCard = new Scanner(System.in);
            String Id = IdCard.next();

            String sql = "select flowId flowId, Type type,IdCard idCard,ExamCard examCard,StudentName studentName,location location,Grade grade from examstudent where Idcard = ?";

            List<Student> studentList = QueryUniversal.getForList(Student.class, sql, Id);

            if(studentList.isEmpty()){
                System.out.println("����������֤������");
            }else
                System.out.println(studentList);
        } else
            System.out.println("�������ʹ���");

    }


}
