package com.assignment.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.Traffic;

public interface TrafficDAO extends JpaRepository<Traffic, Long> {

}
