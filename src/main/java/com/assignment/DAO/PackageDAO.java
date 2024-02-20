package com.assignment.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PackageDAO extends JpaRepository<com.assignment.entity.Package, Long> {

	List<com.assignment.entity.Package> findByOrderId(long id);

	@Query("from Package p where p.id = ?1")
	List<com.assignment.entity.Package> findByPackageId(long id);
}
