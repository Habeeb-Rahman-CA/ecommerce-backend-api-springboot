package com.habeeb.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habeeb.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Creating custom methods
    Category findByName(String name);

    boolean existsByName(String name);
}
