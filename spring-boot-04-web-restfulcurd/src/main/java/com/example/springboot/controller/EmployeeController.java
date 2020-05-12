package com.example.springboot.controller;


import com.example.springboot.dao.DepartmentDao;
import com.example.springboot.dao.EmployeeDao;
import com.example.springboot.entities.Department;
import com.example.springboot.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    //SpringBoot提供的日志框架
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;


    @GetMapping("/emps")
    public String list(Model model){
        logger.debug("============ 执行查询用户列表页面list ========");

        Collection<Employee> employeeList = employeeDao.getAll();

        //将其放到请求域中
        model.addAttribute("employeeList",employeeList);

        //thymeleaf默认自动拼接串classpath:/templates/
        return "emp/list";
    }

    /**
     * 员工添加
     * @return
     */
    @GetMapping("/add")
    public String addUser(Model model){
        logger.debug("============ 用户添加页面addUser ========");
        //先查询出来用户的列表
        Collection<Department> departmentsList = departmentDao.getDepartments();
        //添加到请求域中
        model.addAttribute("departmentsList",departmentsList);
        return "emp/add";
    }

    /**
     * 用户新增页面
     * @param employee
     *     SpringMVC自动将请求参数和入参对象的属性进行一一绑定
     *     要求请求参数的名字和JavaBean入参的对象里面的属性名称一致
     *     即表单页面中的字段名称与Employee实体类中的字段名称一致
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //执行保存操作
        employeeDao.save(employee);
        //redirect：表示重定向到一个地址， /代表当前项目路径
        //forward:表示转发到一个地址
        //添加用户成功之后，跳转到emps页面
        return "redirect:/emps";
    }

    /**
     *点击修改，进入到修改用户界面，同时将修改用户的信息进行回显
     * @param id 路径变量
     * @param model  用户回显修改用户的界面
     * @return
     * /emp/{id}  路径变量指定的形式
     * @PathVariable  表示从路径中获取到指定的参数值
     */
    @GetMapping("/emp/{id}")
    public String modifyEmp(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departmentsList = departmentDao.getDepartments();
        model.addAttribute("departmentsList",departmentsList);

        //返回到修改的页面（此时新增和修改公用了同一个页面）
        return "/emp/add";
    }

    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("==========employee:"+employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        System.out.println("=======id:"+id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
