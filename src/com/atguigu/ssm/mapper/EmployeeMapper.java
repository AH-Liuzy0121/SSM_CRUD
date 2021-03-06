package com.atguigu.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.ssm.bean.Employee;

public interface EmployeeMapper {

	//连表查询所有员工信息
	@Select("SELECT e.id, last_name, email, birth, create_time, department_id as \"dept.id\", department_name as \"dept.deptName\" "
			  + "FROM ssh_employee e "
			  + "LEFT OUTER JOIN ssh_department d "
			  + "ON e.department_id = d.id ")
	public List<Employee> getEmpList();
	
	//向数据库插入一条员工信息
	@Insert("INSERT INTO ssh_employee(last_name, email, birth, create_time, department_id) "
			+ "VALUES(#{lastName},#{email},#{birth},#{createTime},#{dept.id}) ")
	public void saveEmp(Employee employee);

	//从数据库删除指定员工
	@Delete("DELETE FROM ssh_employee WHERE id = #{id}")
	public void removeEmp(@Param("id")Integer id);

	//根据id查找指定员工
	@Select("SELECT e.id,last_name, email, birth, create_time, department_id as \"dept.id\",department_name as \"dept.deptName\" "
			+ "FROM ssh_employee e "
			+ "LEFT OUTER JOIN ssh_department d "
			+ "ON e.department_id = d.id "
			+ "WHERE e.id=#{id}")
	public Employee getEmpById(@Param("id")Integer id);

	//更新员工信息
	@Update("UPDATE ssh_employee e SET last_name = #{lastName},email = #{email},create_time = #{createTime},department_id = #{dept.id} WHERE e.id = #{id}")
	public void updateEmp(Employee employee);

	
}
