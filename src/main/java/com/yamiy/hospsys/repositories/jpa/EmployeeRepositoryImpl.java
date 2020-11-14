package com.yamiy.hospsys.repositories.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.yamiy.hospsys.models.jpa.dao.EmployeeAccount;
import com.yamiy.hospsys.models.jpa.dto.EmployeeAccountRequest;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	
	EntityManager em;

	
	public EmployeeRepositoryImpl(@Autowired EntityManager em) {
		super();
		this.em = em;
	}


	@Override
	public Page<EmployeeAccount> searchEmployeeAccounts(EmployeeAccountRequest request, Pageable pageable) {
//		 CriteriaBuilder builder = em.getCriteriaBuilder();
//
//		 CriteriaQuery<EmployeeAccount> criteria = builder.createQuery(EmployeeAccount.class);
//	        Root<EmployeeAccount> booksRoot = criteria.from(EmployeeAccount.class);
//	        List<Predicate> predicates = new ArrayList<Predicate>();
//
//	        predicates.add(builder.equal(booksRoot.get("id"), request.getId()));
//
//	        predicates.add(builder.like(builder.lower(booksRoot.get("name")), 
//	                    "%" + request.getName().toLowerCase() + "%"));
//
//	        criteria.where(builder.and(predicates.toArray( new Predicate[predicates.size()])));
//
//	        criteria.orderBy(builder.desc(booksRoot.get("id")));
//
//	        // This query fetches the Books as per the Page Limit
//	        List<EmployeeAccount> result = em.createQuery(criteria).getResultList();
//
//	        // Create Count Query
//	        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//	        Root<EmployeeAccount> booksRootCount = countQuery.from(EmployeeAccount.class);
//	        countQuery.select(builder.count(booksRootCount)).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//
//	        // Fetches the count of all Books as per given criteria
//	        Long count = em.createQuery(countQuery).getSingleResult();
//
//	        Page<EmployeeAccount> result1 = new PageImpl<>(result, pageable, count);
//	        return result1;
		return null;
	}
	
}
