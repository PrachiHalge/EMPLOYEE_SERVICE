package com.msedcl.main.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	private int employeeId;
	private String name;
	private double salary;
	private DepartmentDTO departmentDTO;
}