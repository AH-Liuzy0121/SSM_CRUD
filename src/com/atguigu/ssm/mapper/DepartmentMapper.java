package com.atguigu.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.atguigu.ssm.bean.Department;

public interface DepartmentMapper {

	@Select("SELECT id,dept_name FROM ssh_department")
	public List<Department> getDeptList();
}
