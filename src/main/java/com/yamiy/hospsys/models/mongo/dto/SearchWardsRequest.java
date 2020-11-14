package com.yamiy.hospsys.models.mongo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchWardsRequest {
	private List<String> ids;
	private List<Long> wardNumbers;
	private String ward;
	private Long headOfDepartmentNumber;
	private String headOfDepartmentName;
	private String headOfDepartmentSurename;
}
