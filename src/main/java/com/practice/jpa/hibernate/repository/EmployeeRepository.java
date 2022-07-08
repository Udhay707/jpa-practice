package com.practice.jpa.hibernate.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.jpa.hibernate.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	List<Employee> findByNameAndLocation(String name, String location);

	List<Employee> findByNameContaining(String name, Sort sort);

	List<Employee> findEmployeeByNameOrLocation(String name, String location);

	List<Employee> findByName(String name);

	
}
