package com.ritesh.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ritesh.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Search products by category
    List<Product> findByCategoryIgnoreCase(String category);

}