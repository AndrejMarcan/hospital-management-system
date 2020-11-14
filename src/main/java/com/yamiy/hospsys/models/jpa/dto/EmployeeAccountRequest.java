package com.yamiy.hospsys.models.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAccountRequest {
	private String name;
	private Long id;
	private Long wardId;
}
