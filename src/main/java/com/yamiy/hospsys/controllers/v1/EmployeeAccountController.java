package com.yamiy.hospsys.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.models.jpa.dao.EmployeeAccount;
import com.yamiy.hospsys.models.jpa.dto.EmployeeAccountRequest;
import com.yamiy.hospsys.models.mongo.dto.CreateEmployeeRequest;
import com.yamiy.hospsys.models.mongo.dto.RestEmployee;
import com.yamiy.hospsys.services.EmployeeService;
import com.yamiy.hospsys.services.jpa.EmployeeAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

//@RestController
@RequestMapping("/v1")
public class EmployeeAccountController {
	private final EmployeeAccountService employeeAccountService;

	public EmployeeAccountController(@Autowired EmployeeAccountService employeeAccountService) {
		super();
		this.employeeAccountService = employeeAccountService;
	}
	
	@PostMapping(
			value = "/employee/account/create/", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Create new employee account",
			description = "Create new Employee Account based on provided request",
			responses = {
					@ApiResponse(
							responseCode = "201", 
							description = "CREATED", 
							content = @Content(schema = @Schema(implementation = EmployeeAccount.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Bad Request", 
							content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal Server Error", 
							content = @Content(schema = @Schema(hidden = true)))
			})
	public ResponseEntity<EmployeeAccount> createEmployeeAccount(@RequestBody(required = true) EmployeeAccount request) throws BadRequestException {
		
		/** Return response */
		return ResponseEntity.ok(employeeAccountService.createEmployeeAccount(request));
	}
	
	@PostMapping(
			value = "/employee/account/search/", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Create new employee account",
			description = "Create new Employee Account based on provided request",
			responses = {
					@ApiResponse(
							responseCode = "201", 
							description = "CREATED", 
							content = @Content(schema = @Schema(implementation = EmployeeAccount.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Bad Request", 
							content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal Server Error", 
							content = @Content(schema = @Schema(hidden = true)))
			})
	public ResponseEntity<List<EmployeeAccount>> createEmployeeAccount(@RequestBody(required = true) EmployeeAccountRequest request) throws BadRequestException {
		
		/** Return response */
		return ResponseEntity.ok(employeeAccountService.searchEmployeeAccount(request));
	}
}