package com.atguigu.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.ssm.bean.Employee;
import com.atguigu.ssm.mapper.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	
	@Transactional(readOnly=true)
	public List<Employee> getEmpList() {

		return employeeMapper.getEmpList();
	}
	
	@Transactional
	public void saveEmp(Employee employee){
		
		employeeMapper.saveEmp(employee);
	}

	@Transactional
	public void removeEmp(Integer id) {
		
		employeeMapper.removeEmp(id);
	}

	@Transactional
	public Employee getEmpById(Integer id) {
		
		return employeeMapper.getEmpById(id);
	}

	public void updateEmp(Employee employee) {
		
		employeeMapper.updateEmp(employee);
	}
		
}
