package com.yamiy.hospsys.repositories.mongo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import com.yamiy.hospsys.models.mongo.dao.Ward;
import com.yamiy.hospsys.models.mongo.dto.SearchWardsRequest;

public class WardRepositoryImpl implements WardReposiotryCustom {
	private final MongoTemplate mongoTemplate;
	
	public WardRepositoryImpl(@Autowired MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public List<Ward> searchWards(SearchWardsRequest request) {
		return mongoTemplate.find(Query.query(createCriteria(request)), Ward.class);
	}
	
	/** Create criteria based on request */
	private Criteria createCriteria(SearchWardsRequest request) {
		List<Criteria> criteria = new ArrayList<>();
		
		/** Search all */
		if (request == null) {
			return new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()]));
		}
		/** Find by IDs */
		if (!CollectionUtils.isEmpty(request.getIds())) {
			criteria.add(Criteria.where("_id").in(request.getIds()));
		}
		/** Find by ward name */
		if (!StringUtils.isBlank(request.getWard())) {
			criteria.add(Criteria.where("ward").is(request.getWard()));
		}
		/** Find by ward numbers */
		if (!CollectionUtils.isEmpty(request.getWardNumbers())) {
			criteria.add(Criteria.where("type").in(request.getWardNumbers()));
		}
		/** Find by head of department number */
		if (request.getHeadOfDepartmentNumber() != null) {
			criteria.add(Criteria.where("headOfDepartment.wardNumber").is(request.getHeadOfDepartmentNumber()));
		}
		/** Find by head of department name */
		if (!StringUtils.isBlank(request.getHeadOfDepartmentName())) {
			criteria.add(Criteria.where("headOfDepartment.privateInformations.name").is(request.getHeadOfDepartmentName()));
		}
		/** Find by head of department surename */
		if (!StringUtils.isBlank(request.getHeadOfDepartmentSurename())) {
			criteria.add(Criteria.where("headOfDepartment.privateInformations.surename").is(request.getHeadOfDepartmentSurename()));
		}

		return criteria.isEmpty()
				? new Criteria()
				: new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()]));
	}
}
