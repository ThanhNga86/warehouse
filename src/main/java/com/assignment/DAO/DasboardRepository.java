package com.assignment.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Order;


public interface DasboardRepository extends JpaRepository<Order, Long>{
  
}
