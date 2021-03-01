package com.certdevops.certdevops;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA will generate implementation code for the most common CRUD operations.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
 
}