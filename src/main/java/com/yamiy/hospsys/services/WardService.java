package com.yamiy.hospsys.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yamiy.hospsys.exceptions.BadRequestException;
import com.yamiy.hospsys.exceptions.NotFoundException;
import com.yamiy.hospsys.mappers.BussinesObjectMapper;
import com.yamiy.hospsys.models.mongo.dao.Ward;
import com.yamiy.hospsys.models.mongo.dto.CreateWardRequest;
import com.yamiy.hospsys.models.mongo.dto.RestEmployee;
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

	/** Get ward detail by its id 
	 * 
	 * @throws NotFoundException 
	 */
	public WardResponse findWard(String id) throws NotFoundException {
		/** Load ward from DB */
		Optional<Ward> ward = wardRepository.findById(id);
		/** Check if ward was found */
		if(ward.isEmpty()) {
			throw new NotFoundException("Ward for given object ID '"+ id +"' was not found");
		}
		/** Return mapped response */
		return mapper.map(ward.get(), WardResponse.class);
	}

	/** Update ward by id */
	public Object updateWard(String id, RestWard request) throws NotFoundException, BadRequestException {
		/** Load ward from DB */
		Optional<Ward> ward = wardRepository.findById(id);
		/** Check if ward was found */
		if(ward.isEmpty()) {
			throw new NotFoundException("Ward for given object ID '"+ id +"' was not found");
		}
		/** UPDATE data */
		WardResponse response = mapper.map(ward.get(), WardResponse.class);
		
		updateHeadOfDepartment(response, response.getHeadOfDepartment(), request.getHeadOfDepartment());
		
		if(!StringUtils.isBlank(request.getWard())) {
			response.setWard(request.getWard());
		}
		
		Long requestWardNumber = request.getWardNumber();
		if(requestWardNumber != null && requestWardNumber >= 10000) {
			if(checkForWardNumberDuplicity(requestWardNumber)){
				throw BadRequestException.createWith("Ward with given ward number already exists.");
			}
			response.setWardNumber(requestWardNumber);
		}
		
		/** Save updated entity */
		Ward updatedWard = wardRepository.save(mapper.map(response, Ward.class));
		/** return mapped response */
		return mapper.map(updatedWard, WardResponse.class);
	}
	
	

	/** Delete ward by its id 
	 * 
	 * @throws NotFoundException 
	 */
	public void deleteWard(String id) throws NotFoundException {
		/** Load ward from DB */
		Optional<Ward> ward = wardRepository.findById(id);
		/** Check if ward was found */
		if(ward.isEmpty()) {
			throw new NotFoundException("Ward for given object ID '"+ id +"' was not found");
		}
		/** Removes object form DB */
		wardRepository.deleteById(id);
	}
	
	/** Search wards based on given request */
	public List<WardResponse> searchWards(SearchWardsRequest request) {
		/** Load data from DB */
		List<Ward> wards = wardRepository.searchWards(request);
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
		Long requestWardNumber = request.getWardNumber();
		if(requestWardNumber == null || requestWardNumber < 10000) {
			throw BadRequestException.createWith("Ward number undefined of in wrong format.");
		} else if(checkForWardNumberDuplicity(requestWardNumber)){
			throw BadRequestException.createWith("Ward with given ward number already exists.");
		}
	}
	
	/** Check for ward number duplicity - true if ward with given number exists */
	private boolean checkForWardNumberDuplicity(Long wardNumber) {
		return wardRepository.findByWardNumber(wardNumber).isPresent();
	}
	
	/** Update head of department 
	 * @throws NotFoundException */
	private void updateHeadOfDepartment(WardResponse response, RestEmployee formerHeadOfDepartment,
			RestEmployee newHeadOfDepartment) throws NotFoundException {
		if(newHeadOfDepartment != null) {
			response.setHeadOfDepartment(newHeadOfDepartment);
			employeeService.updatePositions(formerHeadOfDepartment.getEmployeeNumber(), newHeadOfDepartment.getEmployeeNumber());
		}
	}
}
