package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
