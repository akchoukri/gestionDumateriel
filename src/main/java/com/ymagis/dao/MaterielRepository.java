package com.ymagis.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ymagis.model.Materiel;

import java.util.List;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long>{

	public Materiel getMaterielByIdMateriel(Long idMateriel);

	@Query("select m from Materiel m where m.designation like :x")
	public Page<Materiel> chercherMateriel(@Param("x")String mc, Pageable pageable);
	public List<Materiel> findByDesignation(String mc);
	//public Page<Materiel> findByDesignationLike(String designation, Pageable pageable);


}
