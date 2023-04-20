package com.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Category;

public interface CRepo extends JpaRepository<Category, Long> {

}
