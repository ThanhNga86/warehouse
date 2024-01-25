package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
