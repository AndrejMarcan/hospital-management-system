package com.yamiy.hospsys.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.models.mongo.dto.CreateEmployeeRequest;
import com.yamiy.hospsys.models.mongo.dto.RestEmployee;
import com.yamiy.hospsys.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/v1")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(@Autowired EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping(
			value = "/employee/create/", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Create new employee",
			description = "Create new Employee based on provided request",
			responses = {
					@ApiResponse(
							responseCode = "201", 
							description = "CREATED"),
					@ApiResponse(
							responseCode = "400", 
							description = "Bad Request", 
							content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal Server Error", 
							content = @Content(schema = @Schema(hidden = true)))
			})
	public ResponseEntity<RestEmployee> createEmployee(@RequestBody(required = true) CreateEmployeeRequest request) throws BadRequestException {
		
		/** Validate create request */
		employeeService.validateCreateEmployeeRequest(request);
		/** Return response */
		return ResponseEntity.ok(employeeService.createEmployee(request));
	}
}
