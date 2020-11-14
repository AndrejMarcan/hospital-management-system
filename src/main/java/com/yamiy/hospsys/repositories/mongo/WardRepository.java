package com.yamiy.hospsys.repositories.mongo;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yamiy.hospsys.models.mongo.dao.Ward;

public interface WardRepository extends PagingAndSortingRepository<Ward, String>, WardReposiotryCustom{
	public Optional<Ward> findByWardNumber(Long wardNumber);
}
