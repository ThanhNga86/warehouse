package com.assignment.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageDAO extends JpaRepository<com.assignment.entity.Package, Long> {

	List<com.assignment.entity.Package> findByOrderId(long id);

}
