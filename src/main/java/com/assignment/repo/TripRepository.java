package com.assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Trip;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{

}
