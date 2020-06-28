package com.yejh.controller;

import com.github.pagehelper.PageInfo;
import com.yejh.bean.Employee;
import com.yejh.bean.InfoDTO;
import com.yejh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangwei
 * @Description:
 * @Time: 2019/9/18 星期三 17:46
 **/
@Controller
@RequestMapping("/emps")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 获取一页数据，保存在model，返回到jsp页面
     *
     * @param pageNumber
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/jsp")
    public String getEmpsToJsp(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               Model model) {
        // List<Employee> emps = employeeService.getAll();
        // model.addAttribute("emps",emps);
        PageInfo<Employee> pageInfo = employeeService.getPageEmps(pageNumber, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList());
        return "empList";
    }

    /**
     * 跳转到emps_json页面，此页实现了通过ajax获取获取json数据，并用js解析，操作DOM，展示数据
     *
     * @return
     */
    @RequestMapping("/toJson")
    public String toJson(Model model) {
        return "emps_json";
    }

    /**
     * 返回json数据
     *
     * @param pageNumber
     * @param pageSize
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/json")
    public InfoDTO getEmpsToJson(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                 Model model) {

        // List<Employee> emps = employeeService.getAll();
        // model.addAttribute("emps",emps);
        PageInfo<Employee> pageInfo = employeeService.getPageEmps(pageNumber, pageSize);
        System.out.println(pageInfo);
        return InfoDTO.success().addData("pageInfo", pageInfo);
    }

    @PostMapping()
    @ResponseBody // 后端校验传入的数据格式是否有误
    public InfoDTO save(@Valid Employee employee, BindingResult result) {
        // 如果出错
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors) {
                // 出错的字段名
                String errorField = error.getField();
                // 校验后的错误信息
                String errorMessage = error.getDefaultMessage();
                map.put(errorField, errorMessage);
            }
            return InfoDTO.fail().addData("errorMap", map);
        }
        int res = employeeService.save(employee);
        System.out.println(res);
        if (res > 0) {
            return InfoDTO.success();
        } else {
            return InfoDTO.fail();
        }
    }

    @PostMapping("/check")
    @ResponseBody
    public InfoDTO checkNameExit(String name) {
        //先判断用户名是否符合正则表达式
        String re = "(^[a-zA-Z0-9_]{3,10}$)|(^[\\u2E80-\\u9FFF]{2,5}$)";
        if (!name.matches(re)) {
            return InfoDTO.fail().addData("va_msg", "用户名必须是3-10位字母下划线和数字的组合或者2-5位中文！");
        }

        boolean isExit = employeeService.checkNameExit(name);
        // 此用户未存在
        if (!isExit) {
            return InfoDTO.success();
            // 此用户已存在
        } else {
            return InfoDTO.fail().addData("va_msg", "用户名已存在");

        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public InfoDTO getEmpInfoById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmpById(id);
        if (employee != null) {
            return InfoDTO.success().addData("emp", employee);
        } else {
            return InfoDTO.fail();
        }
    }

    /**
     * 当{id}参数名和要封装的对象属性名一致时，该值也会封装到对象中
     *
     * @param employee
     * @return
     */
    @PutMapping("/{empId}")
    @ResponseBody
    public InfoDTO updateEmp(Employee employee) {
        System.out.println(employee);
        int res = employeeService.updateEmp(employee);
        if (res > 0) {
            return InfoDTO.success();
        } else {
            return InfoDTO.fail();
        }
    }

    /**
     * 删除单个员工
     *
     * @param
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public InfoDTO delEmpById(@PathVariable("id") Integer id) {
        int res = employeeService.delEmpById(id);
        if (res > 0) {
            return InfoDTO.success();
        } else {
            return InfoDTO.fail();
        }
    }

    /**
     * 批量删除员工
     *
     * @param
     * @return
     */
    @DeleteMapping("/dels/{ids}")
    @ResponseBody
    public InfoDTO delEmpBatch(@PathVariable("ids") String ids) {
        int res = employeeService.delEmpBatch(ids);
        if (res > 0) {
            return InfoDTO.success();
        } else {
            return InfoDTO.fail();
        }
    }
}
