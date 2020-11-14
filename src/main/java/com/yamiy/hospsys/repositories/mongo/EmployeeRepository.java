package com.yamiy.hospsys.repositories.mongo;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yamiy.hospsys.models.mongo.dao.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>{
	public Optional<Employee> findByEmployeeNumber(Long employeeNumber);
}
