package com.yamiy.hospsys.models.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWardRequest {
	private String ward;
	private Long wardNumber;
	private Long employeeNumber;
}
