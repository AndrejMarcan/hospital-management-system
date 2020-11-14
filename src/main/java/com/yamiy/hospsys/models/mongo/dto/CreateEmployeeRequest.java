package com.yamiy.hospsys.models.mongo.dto;

import com.yamiy.hospsys.models.Enumerations.EMPLOYEE_POSITION;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
	private Long employeeNumber;
	private RestPersonalInformations personalInformations;
	private Long wardNumber;
	private EMPLOYEE_POSITION position;
}
