package com.bekennft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bekennft.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
