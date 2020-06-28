package com.yejh.controller;
import com.yejh.bean.Department;
import com.yejh.bean.InfoDTO;
import com.yejh.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wangwei
 * @Description:
 * @Time: 2019/9/18 星期三 17:46
 **/
@Controller
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentServiceImpl;
    
    /**
     * 返回json数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public InfoDTO getDepts(){
        List<Department> depts = departmentServiceImpl.getAll();
        return InfoDTO.success().addData("depts",depts);
    }
}
