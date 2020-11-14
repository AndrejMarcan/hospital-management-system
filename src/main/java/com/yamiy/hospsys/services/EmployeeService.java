package com.yamiy.hospsys.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.mappers.BussinesObjectMapper;
import com.yamiy.hospsys.models.mongo.dao.Employee;
import com.yamiy.hospsys.models.mongo.dto.CreateEmployeeRequest;
import com.yamiy.hospsys.models.mongo.dto.RestEmployee;
import com.yamiy.hospsys.models.mongo.dto.RestAddress;
import com.yamiy.hospsys.models.mongo.dto.RestPersonalInformations;
import com.yamiy.hospsys.repositories.mongo.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final BussinesObjectMapper mapper;
	
	public EmployeeService(@Autowired EmployeeRepository employeeRepository, 
						   @Autowired BussinesObjectMapper mapper) {
		super();
		this.employeeRepository = employeeRepository;
		this.mapper = mapper;
	}
	
	/** Create new employee based on provided request 
	 * 
	 * @param request
	 */
	public RestEmployee createEmployee(CreateEmployeeRequest request) {
		/** Map request to DAO */
		Employee newEmployee = mapper.map(request, Employee.class);
		/** Save new entity */
		newEmployee = employeeRepository.save(newEmployee);
		/** Return saved entity mapped to DTO */
		return mapper.map(newEmployee, RestEmployee.class);
	}
	
	/** Find employee by employee number 
	 * 
	 * @param request
	 * @throws BadRequestException 
	 */
	public RestEmployee findByEmployeeNumber(Long employeeNumber) throws BadRequestException {
		/** Load entity from DB */
		Optional<Employee> employee = employeeRepository.findByEmployeeNumber(employeeNumber);
		/** Check if entity for given employee number exists */
		if(!employee.isPresent()) {
			throw BadRequestException.createWith("Employee for given employee number does not exist.");
		}
		/** Return mapped entity */
		return mapper.map(employee.get(), RestEmployee.class);
	}
	
	/** Validate create request form
	 * 
	 * @param request
	 * @throws BadRequestException
	 */
	public void validateCreateEmployeeRequest(CreateEmployeeRequest request) throws BadRequestException {
		/** Validate employee number format and check for its duplicity */
		if(request.getEmployeeNumber() == null || request.getEmployeeNumber() < 100000000) {
			throw BadRequestException.createWith("Employee number undefined or in wrong format.");
		} else if(checkForEmployeeNumberDuplicity(request.getEmployeeNumber())) {
			throw BadRequestException.createWith("Employee with given number is already registered.");
		}
		/** Validate ward number format */
		//TODO create ward API and check if ward with given number exists
		if(request.getWardNumber() == null || request.getWardNumber() < 10000) {
			throw BadRequestException.createWith("Ward number undefined or in wrong format.");
		}
		/** Validate personal informations */
		if(request.getPersonalInformations() == null) {
			throw BadRequestException.createWith("Personal information are undefined.");
		}
		RestPersonalInformations info = request.getPersonalInformations();
		if(StringUtils.isBlank(info.getName())) {
			throw BadRequestException.createWith("Personal information: 'name' undefined.");
		}
		if(StringUtils.isBlank(info.getSurename())) {
			throw BadRequestException.createWith("Personal information: 'surename' undefined.");
		}
		/** Validate address in personal informations */
		if(info.getAddress().isEmpty()) {
			throw BadRequestException.createWith("Personal information: 'address' list can not be empty.");
		}
		/** Validate all addresses */
		List<RestAddress> addresses = info.getAddress();
		for(RestAddress address : addresses) {
			if(StringUtils.isBlank(address.getCity())) {
				throw BadRequestException.createWith("Address: 'city' undefined.");
			}
			if(StringUtils.isBlank(address.getStreet())) {
				throw BadRequestException.createWith("Address: 'street' undefined.");
			}
			if(StringUtils.isBlank(address.getZip())) {
				throw BadRequestException.createWith("Address: 'zip' undefined.");
			}
			if(StringUtils.isBlank(address.getState())) {
				throw BadRequestException.createWith("Address: 'state' undefined.");
			}
		}
		
		/** Addresses must be distinct */
		info .setAddress(addresses.stream().distinct().collect(Collectors.toList())); 
	}
	
	/** Check if employee with given number already exists - return true if it does 
	 * 
	 * @param employeeNumber
	 */
	private boolean checkForEmployeeNumberDuplicity(Long employeeNumber) {
		return employeeRepository.findByEmployeeNumber(employeeNumber).isPresent();
	}
}
