package com.assignment.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.entity.Product;
import com.assignment.entity.Traffic;
import com.assignment.entity.WareHouse;
import java.util.List;



public interface WareHouseRepository extends JpaRepository<WareHouse, Long>{
	//c1
//	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
//	Page<Product> findByKeywords(String keywords, Pageable pageable);
	//c2
	@Query("from WareHouse w where w.name like ?1")
	Page<WareHouse> findAllByNameLike(String keywords, Pageable pageable);
	//
	//c1
//	@Query("SELECT o FROM traffic o WHERE o.name LIKE ?1")
//	List<Traffic> findByname(Optional<String> name);
	//c2
	Optional<WareHouse> findById(Long id);
	//
}
