package com.yamiy.hospsys.models.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestWard {
	private String ward;
	private Long wardNumber;
	private RestEmployee headOfDepartment;
}
