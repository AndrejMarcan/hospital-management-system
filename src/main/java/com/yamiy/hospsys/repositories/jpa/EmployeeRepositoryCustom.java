package com.yamiy.hospsys.repositories.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yamiy.hospsys.models.jpa.dao.EmployeeAccount;
import com.yamiy.hospsys.models.jpa.dto.EmployeeAccountRequest;

public interface EmployeeRepositoryCustom {
	public Page<EmployeeAccount> searchEmployeeAccounts(EmployeeAccountRequest request, Pageable pageable);
}
