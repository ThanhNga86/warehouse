package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
