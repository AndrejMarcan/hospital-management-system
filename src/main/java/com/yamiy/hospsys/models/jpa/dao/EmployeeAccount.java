package com.yamiy.hospsys.models.jpa.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class EmployeeAccount {
	
	@Id
	private Long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "employee_number", unique = true)
	private Long employeeNumber;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "ward_id", referencedColumnName = "id")
	private Ward ward;
}
