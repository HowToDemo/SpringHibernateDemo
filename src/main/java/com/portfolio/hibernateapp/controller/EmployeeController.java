package com.portfolio.hibernateapp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.hibernateapp.entity.Employee;
import com.portfolio.hibernateapp.exception.ApiException;
import com.portfolio.hibernateapp.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(value = "Employee", description = "REST API for Employee", tags = { "Employee" })
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping(value="/employee")
	public ResponseEntity<Employee> saveEmployee(HttpServletRequest request,
			@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeRepository.save(employee));
	}
	
	@GetMapping(value="/employees")
	public ResponseEntity<List<Employee>> fetchEmployees(HttpServletRequest request) {
		return ResponseEntity.ok().body(employeeRepository.findAll());
	}
	
	@PutMapping(value="/employee/{id}")
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Employee> updateEmployee(HttpServletRequest request, @PathVariable(name="id",required=true) Long id,@RequestBody Employee employee) {
		try {
			Optional<Employee> employeeEntity = employeeRepository.findById(id);
			Employee employeeObj = employeeEntity.get();
			return ResponseEntity.ok().body(employeeRepository.save(employeeObj));
		} catch(ApiException ae) {
			throw new ApiException("Employee with id: "+id+" cannot be found");
		}
	}
	
	@DeleteMapping(value="/employee/{id}")
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<Void> deleteEmployee(HttpServletRequest request, @PathVariable(name="id",required=true) Long id) {
		try {
			employeeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch(ApiException ae) {
			throw new ApiException("Employee with id: "+id+" cannot be found");
		}
	}
}
