package com.catalogue.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.catalogue.entitie.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public Page<Product> findByDesignationContains(String key, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("update Product p set p.designation = :d , p.prix = :pr , p.quantite = :q where p.id = :i")
	public void update(@Param("d") String designation, 
					   @Param("pr") double prix, 
					   @Param("q") int quantite, 
					   @Param("i") long id);
}

