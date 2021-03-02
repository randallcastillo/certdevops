package com.certdevops.certdevops;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
 
}