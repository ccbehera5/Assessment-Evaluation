package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.entity.Category;



@Service
public interface CService {
	
	List<Category> findPaginated(int pageNo,int PigeSize);

}
