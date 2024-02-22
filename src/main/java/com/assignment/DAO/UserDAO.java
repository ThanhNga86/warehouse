package com.assignment.DAO;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Role;
import com.assignment.entity.User;

public interface UserDAO extends JpaRepository<User, String> {
	
	User findByUsername(String userconfirm);
	List<User> findByActive(boolean active);
	void save(Optional<User> user);
	List<User> findByActiveAndRole(boolean active,Role role);
	List<User> findByActiveAndRoleAndUsernameLike(boolean active,Role role,String username);
 }
