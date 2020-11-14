package com.yamiy.hospsys.models.mongo.dao;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yamiy.hospsys.models.Enumerations.EMPLOYEE_POSITION;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employees")
public class Employee {
	private String _id;
	private Long employeeNumber;
	private PersonalInformations personalInformations;
	private Long wardNumber;
	private EMPLOYEE_POSITION position;
}
