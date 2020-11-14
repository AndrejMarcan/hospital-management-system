package com.yamiy.hospsys.models.jpa.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wards")
public class Ward {
	
	@Id
	private Long id;
	
	@Column(name = "ward_name")
	private String ward;
	
	@Column(name = "ward_number")
	private Long wardNumber;
}
