package com.msedcl.main.employee.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.msedcl.main.employee.dto.DepartmentDTO;

@FeignClient(name = "DEPARTMENT-SERVICE", path = "/departmentapi",fallback =DepartmentFallBack.class )
public interface DepartmentFeignClient {
	@GetMapping("/department")
	public ResponseEntity<DepartmentDTO> getDepartmentByDepartmentId( @RequestParam("departmentId") int departmentId);
}