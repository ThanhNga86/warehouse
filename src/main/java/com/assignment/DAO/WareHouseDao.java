package com.assignment.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.entity.WareHouse;

public interface WareHouseDao extends JpaRepository<WareHouse, Long> {

}
