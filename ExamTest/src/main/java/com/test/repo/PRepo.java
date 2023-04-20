package com.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Product;

public interface PRepo extends JpaRepository<Product, Long> {

}
