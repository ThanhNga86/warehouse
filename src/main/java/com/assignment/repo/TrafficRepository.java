package com.assignment.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.entity.Traffic;





public interface TrafficRepository extends JpaRepository<Traffic, Long>{
	//c1
//	@Query("SELECT o FROM traffic o WHERE o.name LIKE ?1")
//	List<Traffic> findByname(Optional<String> name);
	//c2
	Optional<Traffic> findById(Long id);
	//

}
