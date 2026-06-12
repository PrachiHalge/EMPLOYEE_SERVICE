package com.msedcl.main.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msedcl.main.employee.client.DepartmentFeignClient;
import com.msedcl.main.employee.dto.DepartmentDTO;
import com.msedcl.main.employee.dto.EmployeeDTO;
import com.msedcl.main.employee.dto.EmployeeRequestDTO;

@Service
public class EmployeeService {
	private List<EmployeeDTO> employeeList = new ArrayList<>();
	private DepartmentFeignClient departmentFeignClient;

	public EmployeeService(DepartmentFeignClient departmentFeignClient) {
		super();
		this.departmentFeignClient = departmentFeignClient;
	}

//	public EmployeeDTO addNewEmployee(EmployeeRequestDTO employeeRequestDTO) {
//		ResponseEntity<DepartmentDTO> response = departmentFeignClient
//				.getDepartmentByDepartmentId(employeeRequestDTO.getDepartmentId());
//		EmployeeDTO employeeDTO = new EmployeeDTO(employeeRequestDTO.getEmployeeId(), employeeRequestDTO.getName(),
//				employeeRequestDTO.getSalary(), response.getBody());
//		employeeList.add(employeeDTO);
//		return employeeDTO;
//	}

	public EmployeeDTO addNewEmployee(EmployeeRequestDTO employeeRequestDTO) {
		ResponseEntity<DepartmentDTO> response = departmentFeignClient
				.getDepartmentByDepartmentId(employeeRequestDTO.getDepartmentId());
		if(response != null) {
		EmployeeDTO employeeDTO = new EmployeeDTO(employeeRequestDTO.getEmployeeId(), employeeRequestDTO.getName(),
				employeeRequestDTO.getSalary(), response.getBody());
		employeeList.add(employeeDTO);
		return employeeDTO;
		}
		return null;
	}

	public EmployeeDTO getEmployeeByEmployeeId(int employeeId) {
		return employeeList.stream().filter(e -> e.getEmployeeId()==employeeId ).findFirst().orElseThrow(null);		
	}
}