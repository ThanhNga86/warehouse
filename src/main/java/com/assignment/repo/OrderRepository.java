package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
