package cn.wyy.dao;

import cn.wyy.pojo.Department;
import cn.wyy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/4/30.
 */
@Repository
public class EmployeeDao {

    // 模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<>();  // 创建一个部门表
        employees.put(1001, new Employee(1001, "AA", "AA@qq.com", 1, new Department(101,"教学部")));
        employees.put(1002, new Employee(1002, "BB", "BB@qq.com", 0, new Department(102,"市场部")));
        employees.put(1003, new Employee(1003, "CC", "CC@qq.com", 1, new Department(103,"教研部")));
        employees.put(1004, new Employee(1004, "DD", "DD@qq.com", 0, new Department(104,"运营部")));
        employees.put(1005, new Employee(1005, "EE", "EE@qq.com", 1, new Department(105,"后勤部")));
    }

    // 模拟主键自增
    private static Integer initId = 1006;
    // 增加员工
    public void addEmp(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId);
        }

        // 因为<select>的原因只传回了一个department的id所以需要通过这个id找到对应的department的内容
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    // 通过id删除员工
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }
}
