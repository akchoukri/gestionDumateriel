package com.ymagis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ymagis.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

//	@Query("select * from CATEGORIE where NOM_CAT = : cat")
	public Categorie findByNomCategorie(String nomCategorie);
}
