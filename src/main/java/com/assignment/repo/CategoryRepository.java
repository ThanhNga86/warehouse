package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
