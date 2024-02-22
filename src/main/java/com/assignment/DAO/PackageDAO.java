package com.assignment.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment.entity.Package;

public interface PackageDAO extends JpaRepository<Package, Long> {

	List<Package> findByOrderId(long id);
	Package findById(long id);
}
