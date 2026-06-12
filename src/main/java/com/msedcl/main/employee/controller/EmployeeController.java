package com.msedcl.main.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.employee.dto.EmployeeDTO;
import com.msedcl.main.employee.dto.EmployeeRequestDTO;
import com.msedcl.main.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("employeeapi")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping("employee")
	public ResponseEntity<EmployeeDTO> addNewEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		EmployeeDTO employeeDTO = employeeService.addNewEmployee(employeeRequestDTO);
		if (employeeDTO != null) {
			return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("employee/{employeeId}")
	public ResponseEntity<EmployeeDTO> getEmployeeByEmployeeId(@PathVariable int employeeId) {
		EmployeeDTO employeeDTO = employeeService.getEmployeeByEmployeeId(employeeId);
		if (employeeDTO != null) {
			return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}