package com.assignment.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.entity.Product;
import com.assignment.entity.WareHouse;


public interface WareHouseRepository extends JpaRepository<WareHouse, Long>{
	//c1
//	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
//	Page<Product> findByKeywords(String keywords, Pageable pageable);
	//c2
	@Query("from WareHouse w where w.name like ?1")
	Page<WareHouse> findAllByNameLike(String keywords, Pageable pageable);
	//
}
