package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.entity.Category;
import com.test.repo.CRepo;


@Service
public class ServiceImpl implements CService {
	
	@Autowired
	private CRepo repo;

	@Override
	public List<Category> findPaginated(int pageNo, int PigeSize) {
		Pageable paging=PageRequest.of(pageNo, PigeSize) ;
		Page<Category> pagedResult = repo.findAll(paging);
		return pagedResult.toList();
	}

}
