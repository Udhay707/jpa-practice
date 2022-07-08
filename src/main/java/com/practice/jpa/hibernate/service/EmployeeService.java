package com.practice.jpa.hibernate.service;

import java.util.List;

import com.practice.jpa.hibernate.entity.Employee;

public interface EmployeeService {

	List<Employee> getEmployee(Integer pageNumber, Integer pageSize);
	
	Employee saveEmployee(Employee emp);
	
	Employee getEmployeeById(Integer id);
	
	String deleteEmployeeById(Integer id);
	
	Employee updateEmployee(Employee emp);
	
	List<Employee> filterByName(String name);
	
	List<Employee> getEmployeeByNameAndLocation(String name, String location);
	
	List<Employee> getEmployeeMatch(String name);
	
	List<Employee> getByNameOrLocation(String name, String location);
}
