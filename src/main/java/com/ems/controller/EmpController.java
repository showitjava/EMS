package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Employee management controller")
public class EmpController {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/hello")
	public String sayhello()
	{
		return "Hello from local to AWS!!!";
	}
	
	// Get all the employee list
	@Operation(summary = "Get all the employees",description = "It Will fetch all the employee from db")
	@GetMapping("/employees/list")
	public List<Employee> allEmployeeList()
	{
		return service.getAllEmployee();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable int id) throws EmployeeNotFoundException
	{
		Employee emp = service.getEmployeeById(id);
		
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}
	
	

	@PostMapping("/employees/create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		Employee emp =service.createEmployee(employee);
		return new ResponseEntity<>(emp,HttpStatus.CREATED);
	}
	
	@PutMapping("/employees/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id ,@RequestBody Employee employee) throws Exception
	{
		Employee emp =service.updateEmployeeById(id,employee);
		return new ResponseEntity<>(emp,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/employees/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id ) throws Exception
	{
		boolean flag =service.deleteEmployeeById(id);
		
		if(flag == true)
		{
			return new ResponseEntity<>("Delete the employee!",HttpStatus.OK);
		}
		return new ResponseEntity<>("Employee not Deleted!!",HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

}
