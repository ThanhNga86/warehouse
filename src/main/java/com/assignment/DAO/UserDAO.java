package com.assignment.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.User;

public interface UserDAO extends JpaRepository<User, String> {
	
	User findByUsername(String userconfirm);
	List<User> findByActive(boolean active);
	void save(Optional<User> user);
	
 }
