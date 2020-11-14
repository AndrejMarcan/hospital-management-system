package com.yamiy.hospsys.models.mongo.dao;

import com.yamiy.hospsys.models.Enumerations.ADDRESS_TYPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private ADDRESS_TYPE type;
	private String city;
	private String street;
	private String zip;
	private String state;
}
