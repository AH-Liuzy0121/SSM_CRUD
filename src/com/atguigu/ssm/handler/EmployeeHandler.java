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
	public String getEmpList(Map<String, Object> map) {

		List<Employee> empList = employeeService.getEmpList();

		map.put("empList", empList);

		return "list";
	}

	@RequestMapping("/emp/addUI")
	public String addUI(Map<String, Object> map) {
		List<Department> deptList = departmentService.getDeptList();
		map.put("deptList", deptList);

		map.put("employee", new Employee());

		return "addUI";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String saveEmp(Employee employee) {

		employeeService.saveEmp(employee);

		return "redirect:/emp/list";
	}

	@ResponseBody
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String removeEmp(@PathVariable("id") Integer id) {

		employeeService.removeEmp(id);

		return "1";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String editUI(@PathVariable("id") Integer id, Map<String, Object> map) {

		Employee employee = employeeService.getEmpById(id);
		map.put("employee", employee);

		List<Department> deptList = departmentService.getDeptList();
		map.put("deptList", deptList);

		return "editUI";
	}

	@ModelAttribute
	public Employee getEmployee(
			@RequestParam(value = "id", required = false) Integer id) {

		Employee employee = employeeService.getEmpById(id);

		return (id == null) ? new Employee() : employee;
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String updateEmp(Employee employee){
		
		employeeService.updateEmp(employee);
		
		return "redirect:/emp/list";
	}
}
