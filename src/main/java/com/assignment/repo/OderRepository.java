package com.assignment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Order;


public interface OderRepository extends JpaRepository<Order, Long>{
}
