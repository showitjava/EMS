package com.ems.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository  repo;

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		List<Employee> list= new ArrayList<>();
		
//		list.add(new Employee(1,"Kishan","Patel","kk@gmail.com","IT"));
//		list.add(new Employee(1,"Abc","Patel","abc@gmail.com","IT"));
//		list.add(new Employee(1,"xyz","Patel","xyz@gmail.com","IT"));
//		list.add(new Employee(1,"Kishan","Patel","kk@gmail.com","IT"));
//		list.add(new Employee(1,"Abc","Patel","abc@gmail.com","IT"));
//		list.add(new Employee(1,"xyz","Patel","xyz@gmail.com","IT"));
		
		list =repo.findAll();
		
		
		return list;
	}

	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
		
		return repo.findById(id).orElseThrow(
				()->new EmployeeNotFoundException("Employee with ID "+id +"not found"));
		
	}

	public Employee createEmployee(Employee employee) {
		
		Employee emp = repo.save(employee);
		
		return emp;
	}

	public Employee updateEmployeeById(int id, Employee employee) throws Exception {
		
		
		Optional<Employee> emp =repo.findById(id);
		if(emp.isPresent())
		{
			Employee emp_main=emp.get();
			
			emp_main.setFirstName(employee.getFirstName());
			emp_main.setLastName(employee.getLastName());
			emp_main.setEmail(employee.getEmail());
			emp_main.setDepartment(employee.getDepartment());
			
			return repo.save(emp_main);
			
		}
		else {
			throw new Exception("Employee not found !!");
		}
		
	}

	public boolean deleteEmployeeById(int id) {
		
		Optional<Employee> emp =repo.findById(id);
		if(emp.isPresent())
		{
			repo.deleteById(id);
			return true;
		}
			
		return false;
		
		
		
	}
	
	
	

}
