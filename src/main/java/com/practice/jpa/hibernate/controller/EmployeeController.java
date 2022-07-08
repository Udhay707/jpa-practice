package com.practice.jpa.hibernate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.jpa.hibernate.entity.Employee;
import com.practice.jpa.hibernate.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl empService;
	@Value("${app.name:Employee tracker}")
	private String appName;
	
	@Value("${app.version:version 1}")
	private String appVersion;
	
	@GetMapping("/version")
	public ResponseEntity<String> getVersion() {
		return new ResponseEntity<String>(appName+"-"+appVersion,HttpStatus.OK);
	}

    @GetMapping(value="/employees")
    public ResponseEntity< List<Employee> > getAll(
    		@RequestParam("page") Integer pageNumber, @RequestParam Integer size){
        return new ResponseEntity<List<Employee>>(
        		empService.getEmployee(pageNumber, size), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity< Employee > getById(@PathVariable("id") Integer id){
        return new ResponseEntity<Employee>(
        		empService.getEmployeeById(id), HttpStatus.OK);
    }

    @DeleteMapping("/employees/delete") // ?id=10
    public ResponseEntity<HttpStatus> deleteById(@RequestParam("id") Integer id){
        empService.deleteEmployeeById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateById(@PathVariable Integer id, @Valid @RequestBody Employee emp) {
    	emp.setId(id);
    	return new ResponseEntity<Employee>(
    			empService.updateEmployee(emp), HttpStatus.OK);
    }
    
    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employee) {
    	empService.saveEmployee(employee);
    	return new ResponseEntity<String>(
    			"Employee saved with name "+employee.getName(), HttpStatus.CREATED);
    }
    
    @GetMapping("/filterbyname")
    public ResponseEntity<List<Employee>> filterByName(@RequestParam String name){
    	return new ResponseEntity<List<Employee>>(
    			empService.filterByName(name), HttpStatus.OK
    			);
    }
    
    @GetMapping("/filterbynameandlocation")
    public ResponseEntity<List<Employee>> filterByNameAndLocation
    					(@RequestParam String name,@RequestParam String location){
    	return new ResponseEntity<List<Employee>>(
    			empService.getEmployeeByNameAndLocation(name, location),HttpStatus.OK
    			);
    }
    
    @GetMapping("/getmatchingemployee")
    public ResponseEntity<List<Employee>> filterbyKeyword(@RequestParam String name){
    	return new ResponseEntity<List<Employee>>(
    			empService.getEmployeeMatch(name),
    			HttpStatus.OK
    			);
    }
    
    @GetMapping("/employee/{name}/{location}")
    public ResponseEntity<List<Employee>> filterbyNameOrLocation
    	(@PathVariable String name, @PathVariable String location){
    	return new ResponseEntity<List<Employee>>(
    			empService.getByNameOrLocation(name, location),
    			HttpStatus.OK
    			);
    }
}
