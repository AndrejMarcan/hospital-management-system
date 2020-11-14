package com.yamiy.hospsys.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.exceptions.NotFoundException;
import com.yamiy.hospsys.exceptions.PlannerVndErrors;

@SuppressWarnings("deprecation")
@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
@ResponseBody
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
	public ResponseEntity<VndError[]> badRequestException(final BadRequestException e) {
		PlannerVndErrors errs = new PlannerVndErrors();
		for (String errorMsg : e.getErrorMessages()) {
			errs.addError("Bad Request", errorMsg);
		}
		
		return new ResponseEntity<>(errs.toErrors(), HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	public ResponseEntity<VndError[]> notFoundException(final NotFoundException e) {
		return new ResponseEntity<>(
				new PlannerVndErrors(
						StringUtils.isAllBlank(e.getResourceId()) ? "Content Not Found" : e.getResourceId(),
						e.getMessage()).toErrors(), 
						HttpStatus.NOT_FOUND);
	}
}
