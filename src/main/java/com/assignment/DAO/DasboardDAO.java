package com.assignment.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Order;
import com.assignment.entity.User;

public interface DasboardDAO extends JpaRepository<Order, Long> {
	
	List<Order> findByUserCustomer(User user);
	;
	
}
