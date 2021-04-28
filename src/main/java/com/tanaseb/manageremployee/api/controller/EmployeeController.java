package com.tanaseb.manageremployee.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanaseb.manageremployee.api.model.CreateRequest;
import com.tanaseb.manageremployee.api.model.CreateResponse;
import com.tanaseb.manageremployee.api.model.UpdateRequest;
import com.tanaseb.manageremployee.api.model.UpdateResponse;
import com.tanaseb.manageremployee.application.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Operation(summary = "Create an employee")
	@PostMapping
	public ResponseEntity<CreateResponse> create(@RequestBody @Valid CreateRequest request) {
		return ResponseEntity.ok(employeeService.create(request));
	}

	@Operation(summary = "Update the state of an employee")
	@PutMapping
	public ResponseEntity<UpdateResponse> update(@RequestBody @Valid UpdateRequest request) {
		return ResponseEntity.ok(employeeService.update(request));
	}
}
