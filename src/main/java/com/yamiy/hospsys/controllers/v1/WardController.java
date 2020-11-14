package com.yamiy.hospsys.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.models.mongo.dto.CreateWardRequest;
import com.yamiy.hospsys.models.mongo.dto.SearchWardsRequest;
import com.yamiy.hospsys.models.mongo.dto.WardResponse;
import com.yamiy.hospsys.services.WardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/v1")
public class WardController {
	private final WardService wardService;

	public WardController(@Autowired WardService wardService) {
		super();
		this.wardService = wardService;
	}
	
	@PostMapping(
			value = "/ward/create/", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Register new ward",
			description = "Register new ward based on provided request",
			responses = {
					@ApiResponse(
							responseCode = "201", 
							description = "CREATED", 
							content = @Content(schema = @Schema(implementation = WardResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Bad Request", 
							content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal Server Error", 
							content = @Content(schema = @Schema(hidden = true)))
			})
	public ResponseEntity<WardResponse> createWard(@RequestBody(required = true) CreateWardRequest request) throws BadRequestException {
		
		/** Validate create request */
		wardService.validateCreateWardRequest(request);
		/** Return response */
		return new ResponseEntity<>(wardService.createWard(request), HttpStatus.CREATED);
	}
	
	@PostMapping(
			value = "/ward/search/", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Search for wards",
			description = "Search for wards based on request",
			responses = {
					@ApiResponse(
							responseCode = "200", 
							description = "OK", 
							content = @Content(schema = @Schema(implementation = WardResponse.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "Bad Request", 
							content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(
							responseCode = "500", 
							description = "Internal Server Error", 
							content = @Content(schema = @Schema(hidden = true)))
			})
	public ResponseEntity<List<WardResponse>> searchWards(@RequestBody(required = false) SearchWardsRequest request) throws BadRequestException {

		/** Return response */
		return ResponseEntity.ok(wardService.searchWards(request));
	}
}

