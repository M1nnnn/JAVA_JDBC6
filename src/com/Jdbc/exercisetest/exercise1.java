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
        System.out.println("请输入用户名：");
        String name = sc.next();
        System.out.println("请输入邮箱：");
        String email = sc.next();
        System.out.println("请输入生日：");
        String birth = sc.next();

        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        int update = UpdateOperate.update(sql, name, email, birth);
        System.out.println(update + "条数据被修改");
        if (update > 0) {
            System.out.println("添加成功");
        } else
            System.out.println("添加失败");


    }

    @Test
    /**
     * 功能描述: <br>
     * 〈〉建立 java 程序：输入身份证号或准考证号可以查询到学生的基本信息。
     * @Param:
     * @Return:
     * @Author: itestar
     * @Date: 2022/10/5 19:24
     */
    public void queryTest() {
        System.out.println("请输入您要输入的类型：");
        System.out.println("a.准考证号" + "\n" + "b.身份证号");
        Scanner sc = new Scanner(System.in);
        String select = sc.next();

        if ("a".equalsIgnoreCase(select)) {
            System.out.println("请输入准考证号：");
            Scanner exameId = new Scanner(System.in);
            String exame = exameId.next();

            String sql = "select flowId flowId, Type type,IdCard idCard,ExamCard examCard,StudentName studentName,location location,Grade grade from examstudent where examcard = ?";

            List<Student> studentList = QueryUniversal.getForList(Student.class, sql, exame);

            if(studentList.isEmpty()){
                System.out.println("您输入的准考证号证号有误");
            }else{
            System.out.println(studentList);
            }

        } else if ("b".equalsIgnoreCase(select)) {
            System.out.println("请输入身份证号：");
            Scanner IdCard = new Scanner(System.in);
            String Id = IdCard.next();

            String sql = "select flowId flowId, Type type,IdCard idCard,ExamCard examCard,StudentName studentName,location location,Grade grade from examstudent where Idcard = ?";

            List<Student> studentList = QueryUniversal.getForList(Student.class, sql, Id);

            if(studentList.isEmpty()){
                System.out.println("您输入的身份证号有误");
            }else
                System.out.println(studentList);
        } else
            System.out.println("输入类型错误");

    }


}
