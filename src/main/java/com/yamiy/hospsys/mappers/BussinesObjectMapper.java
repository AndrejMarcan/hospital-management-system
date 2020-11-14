package com.yamiy.hospsys.mappers;

import org.springframework.stereotype.Component;

import com.yamiy.hospsys.models.mongo.dao.Address;
import com.yamiy.hospsys.models.mongo.dao.Employee;
import com.yamiy.hospsys.models.mongo.dao.PersonalInformations;
import com.yamiy.hospsys.models.mongo.dao.Ward;
import com.yamiy.hospsys.models.mongo.dto.CreateEmployeeRequest;
import com.yamiy.hospsys.models.mongo.dto.RestAddress;
import com.yamiy.hospsys.models.mongo.dto.RestEmployee;
import com.yamiy.hospsys.models.mongo.dto.RestPersonalInformations;
import com.yamiy.hospsys.models.mongo.dto.WardResponse;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class BussinesObjectMapper {
	private final MapperFacade mapperFacade;

	public BussinesObjectMapper() {
		super();
		DefaultMapperFactory fact = new DefaultMapperFactory.Builder().build();
		
		fact.classMap(CreateEmployeeRequest.class, Employee.class).byDefault().register();
		fact.classMap(RestEmployee.class, Employee.class).field("id", "_id").byDefault().register();
		fact.classMap(RestAddress.class, Address.class).byDefault().register();
		fact.classMap(RestPersonalInformations.class, PersonalInformations.class).byDefault().register();
		fact.classMap(WardResponse.class, Ward.class).field("id", "_id").byDefault().register();
		
		this.mapperFacade = fact.getMapperFacade();
	}
	
	public <A, B> B map(A a, Class<B> resultClass) {
		return mapperFacade.map(a, resultClass);
	}
}
