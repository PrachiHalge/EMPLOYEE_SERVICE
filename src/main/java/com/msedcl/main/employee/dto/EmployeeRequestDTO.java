package com.msedcl.main.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
	private int employeeId;
	private String name;
	private double salary;
	private int departmentId;
}