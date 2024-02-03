package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Package;

public interface PackageRepository extends JpaRepository<Package, Long> {

}
