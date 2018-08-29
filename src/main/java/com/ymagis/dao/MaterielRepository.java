package com.ymagis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ymagis.model.Materiel;

public interface MaterielRepository extends JpaRepository<Materiel,Long>{
	//chercher les materiel
	@Query("select m from Materiel m where m.designation like :x")
	public List <Materiel> chercherMateriel(@Param("x")String mc);
}
