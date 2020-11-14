package com.yamiy.hospsys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.mappers.BussinesObjectMapper;
import com.yamiy.hospsys.models.mongo.dao.Ward;
import com.yamiy.hospsys.models.mongo.dto.CreateWardRequest;
import com.yamiy.hospsys.models.mongo.dto.RestWard;
import com.yamiy.hospsys.models.mongo.dto.SearchWardsRequest;
import com.yamiy.hospsys.models.mongo.dto.WardResponse;
import com.yamiy.hospsys.repositories.mongo.WardRepository;

@Service
public class WardService {
	private final WardRepository wardRepository;
	private final BussinesObjectMapper mapper;
	private final EmployeeService employeeService;
	
	

	public WardService(@Autowired WardRepository wardRepository, 
					   @Autowired BussinesObjectMapper mapper,
					   @Autowired EmployeeService employeeService) {
		super();
		this.wardRepository = wardRepository;
		this.mapper = mapper;
		this.employeeService = employeeService;
	}
	
	/** Create new ward based on request 
	 * 
	 * @param request
	 * @throws BadRequestException 
	 */
	public WardResponse createWard(CreateWardRequest request) throws BadRequestException {
		/** New ward DTO object creation and initialization */
		RestWard restWard = new RestWard();
		restWard.setWard(request.getWard());
		restWard.setWardNumber(request.getWardNumber());
		if(request.getEmployeeNumber() != null) {
			restWard.setHeadOfDepartment(employeeService.findByEmployeeNumber(request.getEmployeeNumber()));
		}
		/** Map entity to DAO */
		Ward ward = mapper.map(restWard, Ward.class);
		/** Save new entity */
		ward = wardRepository.save(ward);
		/** Return saved entity */
		return mapper.map(ward, WardResponse.class);
	}
	
	/** Search wards based on given request */
	public List<WardResponse> searchWards(SearchWardsRequest request) {
		/** Load data from DB */
		List<Ward> wards = wardRepository.searchWards(request);
//		if(wards == null) {
//			return new ArrayList<>();
//		}
		/** Return mapped list of wards */
		return wards.stream().map(w -> mapper.map(w, WardResponse.class)).collect(Collectors.toList());
	}
	
	/** Validate create ward request 
	 * 
	 * @param request
	 * @throws BadRequestException 
	 */
	public void validateCreateWardRequest(CreateWardRequest request) throws BadRequestException {
		/** Validate ward name */
		if(StringUtils.isBlank(request.getWard())) {
			throw BadRequestException.createWith("Ward name undefined.");
		}
		/** Validate ward number format and uniqueness */
		if(request.getWardNumber() == null || request.getWardNumber() < 10000) {
			throw BadRequestException.createWith("Ward number undefined of in wrong format.");
		} else if(checkForWardNumberDuplicity(request.getWardNumber())){
			throw BadRequestException.createWith("Ward with given ward number already exists.");
		}
	}
	
	/** Check for ward number duplicity - true if ward with given number exists */
	private boolean checkForWardNumberDuplicity(Long wardNumber) {
		return wardRepository.findByWardNumber(wardNumber).isPresent();
	}
}
