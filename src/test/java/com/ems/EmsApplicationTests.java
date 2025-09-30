package com.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.services.EmployeeService;

@SpringBootTest
class EmsApplicationTests {
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	EmployeeRepository empRepo;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void firstTest()
	{
		
		assertEquals(100, 50+50);
	}
	
	@Test
	public void testGetEmployeeId() throws EmployeeNotFoundException
	{
		
		Employee emp = empService.getEmployeeById(22);
		
		assertEquals("Runali", emp.getFirstName());
		
	}
	
}
