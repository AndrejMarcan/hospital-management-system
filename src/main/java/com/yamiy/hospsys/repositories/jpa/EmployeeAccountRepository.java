package com.yamiy.hospsys.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yamiy.hospsys.models.jpa.dao.EmployeeAccount;

public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {//, EmployeeRepositoryCustom {

}
