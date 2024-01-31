package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerVO;

@Repository
@EnableJpaRepositories
public interface CustomerDAO extends JpaRepository<CustomerVO, Integer> {
	
//	@Query("select nvl(count(*), 0)+1 from CustomerVO")
	@Query(value="select nvl(count(*), 0)+1 from customer", nativeQuery=true)
	public int getNextNo();
}
