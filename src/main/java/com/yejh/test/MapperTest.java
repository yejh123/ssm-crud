package com.yejh.test;/**
 * @author yejh
 * @create 2019-12_19 23:50
 */

import com.yejh.bean.Department;
import com.yejh.bean.Employee;
import com.yejh.controller.DepartmentController;
import com.yejh.dao.DepartmentMapper;
import com.yejh.dao.EmployeeMapper;
import com.yejh.service.DepartmentService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

/**
 * @description: TODO
 **/

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Autowired
    DepartmentService departmentService;


    @Test
    public void test(){
        System.out.println(this.departmentMapper);
        System.out.println(departmentService);
        System.out.println("helloworld");
    }

    @Test
    public void CRUDTest(){

//        departmentMapper.insertSelective( new Department(null, "开发部"));
//        departmentMapper.insertSelective( new Department(null, "测试版"));

        //获取可以进行批量操作的SqlSession
        //https://mybatis.org/spring/zh/sqlsession.html#SqlSessionTemplate
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Integer[] depts = new Integer[]{1,2};
        for(int i = 0; i < 1000; ++i){
            String s = UUID.randomUUID().toString().substring(0, 5) + "i";
            Employee employee = new Employee(null, s, "M", s + "@qq.com", depts[new Random().nextInt(2)], null);
            mapper.insertSelective(employee);

        }

    }
}
