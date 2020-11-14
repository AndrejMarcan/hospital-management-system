package com.yamiy.hospsys.services.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yamiy.hospsys.models.jpa.dao.EmployeeAccount;
import com.yamiy.hospsys.models.jpa.dto.EmployeeAccountRequest;
import com.yamiy.hospsys.repositories.jpa.EmployeeAccountRepository;

@Service
public class EmployeeAccountService {
	private final EmployeeAccountRepository empAccRepo;

	public EmployeeAccountService(@Autowired EmployeeAccountRepository empAccRepo) {
		super();
		this.empAccRepo = empAccRepo;
	}
	
	@Transactional
	public EmployeeAccount createEmployeeAccount(EmployeeAccount request) {
		return empAccRepo.save(request);
	}
	
	public List<EmployeeAccount> searchEmployeeAccount(EmployeeAccountRequest request) {
		List<String> sort = new ArrayList<>();
		sort.add("id");
		Pageable pageable = Pageable.unpaged();
		return null;
		//return empAccRepo.searchEmployeeAccounts(request, pageable).stream().collect(Collectors.toList());
	}
}
