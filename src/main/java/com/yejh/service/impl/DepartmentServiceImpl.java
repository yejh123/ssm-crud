package com.yejh.service.impl;

import com.yejh.bean.Department;
import com.yejh.dao.DepartmentMapper;
import com.yejh.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangwei
 * @Description:
 * @Time: 2019/9/19 星期四 00:33
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }
    
}
