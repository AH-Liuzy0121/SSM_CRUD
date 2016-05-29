package com.atguigu.ssm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.ssm.bean.Department;
import com.atguigu.ssm.bean.Employee;
import com.atguigu.ssm.service.DepartmentService;
import com.atguigu.ssm.service.EmployeeService;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/emp/list")
	//获取所有的员工信息
	public String getEmpList(Map<String, Object> map) {

		List<Employee> empList = employeeService.getEmpList();

		map.put("empList", empList);

		return "list";
	}

	@RequestMapping("/emp/addUI")
	//进入添加员工的页面
	public String addUI(Map<String, Object> map) {
		//为了给员工划分部门，所以需要公司的所有部门
		List<Department> deptList = departmentService.getDeptList();
		map.put("deptList", deptList);

		map.put("employee", new Employee());

		return "addUI";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	//员工添加
	public String saveEmp(Employee employee) {

		employeeService.saveEmp(employee);

		return "redirect:/emp/list";
	}

	@ResponseBody
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	//删除员工，加上@ResponseBody注解，不会再返回值上添加前后缀，使用Ajax在页面进行删除
	public String removeEmp(@PathVariable("id") Integer id) {

		//从数据库中删除
		employeeService.removeEmp(id);

		return "1";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	//进入编辑页面，根据id获取员工信息
	public String editUI(@PathVariable("id") Integer id, Map<String, Object> map) {

		Employee employee = employeeService.getEmpById(id);
		map.put("employee", employee);

		List<Department> deptList = departmentService.getDeptList();
		map.put("deptList", deptList);

		return "editUI";
	}

	@ModelAttribute
	//添加@ModelAttribute注解，在更新前会调用该方法获取Employee对象
	public Employee getEmployee(
			@RequestParam(value = "id", required = false) Integer id) {

		Employee employee = employeeService.getEmpById(id);

		//判断id是否存在，若不存在就是添加，返回空的Employee对象，若id存在就是更新
		//根据指定的id获取员工信息
		return (id == null) ? new Employee() : employee;
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	//员工更新
	public String updateEmp(Employee employee){
		
		employeeService.updateEmp(employee);
		
		return "redirect:/emp/list";
	}
}
