package com.yejh.service;

import com.yejh.bean.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangwei
 * @Description:
 * @Time: 2019/9/19 星期四 00:32
 **/

@Service
public interface DepartmentService {
    /**
     * 查出所有部门信息
     * @return
     */
    List<Department> getAll();
    
}
