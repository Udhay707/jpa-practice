package com.practice.jpa.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.practice.jpa.hibernate.entity.Employee;
import com.practice.jpa.hibernate.exception.IdNotFoundException;
import com.practice.jpa.hibernate.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public List<Employee> getEmployee(Integer pageNumber, Integer pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
		return repo.findAll(page).getContent();
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return repo.save(emp);
	}

	@Override
	public Employee getEmployeeById(Integer id) {

		return repo.findById(id).
				orElseThrow(()->new IdNotFoundException("Id "+id+" is not found"));
	}

	@Override
	public String deleteEmployeeById(Integer id) {
		
		try {
			
			repo.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Id "+id+" is not found");
		}
		
		return "Employee with id" + id + " is deleted successfully";
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Employee newEmp = null;
		try {
			newEmp = repo.save(emp);
			
		} catch (Exception e) {
			throw new RuntimeException("Employee not found");
		}
		return newEmp;
	}

	@Override
	public List<Employee> filterByName(String name) {
		
		return repo.findByName(name);
	}

	@Override
	public List<Employee> getEmployeeByNameAndLocation(String name, String location) {
		return repo.findByNameAndLocation(name, location);
	}

	@Override
	public List<Employee> getEmployeeMatch(String name) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return repo.findByNameContaining(name, sort);
	}

	@Override
	public List<Employee> getByNameOrLocation(String name, String location) {
		return repo.findEmployeeByNameOrLocation(name, location);
	}

}
