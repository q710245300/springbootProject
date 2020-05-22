package cn.wyy.controller;

import cn.wyy.dao.DepartmentDao;
import cn.wyy.dao.EmployeeDao;
import cn.wyy.pojo.Department;
import cn.wyy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Created by Enzo Cotter on 2020/5/2.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    // 员工列表页面
    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> all = employeeDao.getAll();

        model.addAttribute("emps", all);
        return "emp/list";
    }

    // 跳转到添加员工的界面
    @GetMapping("/addemp")
    public String addEmpPage(Model model) {
        // 在添加员工界面中查出所有部门的数据方便选择用户的部门
        model.addAttribute("departments", departmentDao.getDepartments());

        return "emp/add";
    }

    // 执行添加新员工操作
    @PostMapping("/addemp")
    public String addEmp(Employee employee) {

        System.out.println(employee);
        employeeDao.addEmp(employee);
        // 这里不能return list.html是因为html是一个静态的页面，想要展示员工列表需先去dao中查询数据，再返回，所以应该直接指向empsController
        return "redirect:/emps";
    }

    // 跳转到员工的修改页面
    @GetMapping("/emp/{id}")
    //  通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
    public String updateEmpPage(@PathVariable("id") Integer id, Model model) {
        // 查出需要修改的员工信息
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);

        // 修改用户信息的时候可能需要修改部门信息(通过下拉菜单)，所以还是需要将所有部门查出来
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    // 执行修改选中信息修改的操作,但是因为需要保证隐私性所以要用post方式提交
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        // 直接覆盖原有的用户
        employeeDao.addEmp(employee);
        return "redirect:/emps";
    }

    // 执行删除用户操作
    @GetMapping("/delemp/{id}")
    public String delEmp(@PathVariable("id") Integer id) {
        employeeDao.deleteEmployeeById(id);
        return "redirect:/emps";
    }
}
