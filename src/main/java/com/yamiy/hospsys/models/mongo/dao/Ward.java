package com.yamiy.hospsys.models.mongo.dao;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wards")
public class Ward {
	private String _id;
	private String ward;
	private Long wardNumber;
	private Employee headOfDepartment;
}
