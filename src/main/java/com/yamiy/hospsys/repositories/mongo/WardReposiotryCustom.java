package com.yamiy.hospsys.repositories.mongo;

import java.util.List;

import com.yamiy.hospsys.models.mongo.dao.Ward;
import com.yamiy.hospsys.models.mongo.dto.SearchWardsRequest;

public interface WardReposiotryCustom {
	public List<Ward> searchWards(SearchWardsRequest request);
}
