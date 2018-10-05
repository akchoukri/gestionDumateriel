package com.ymagis.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ymagis.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

	@Query("select c from Categorie c where c.nomCategorie = :cat")
	public Optional<Categorie> findByNomCategorie(@Param("cat")String nomCategorie);
}
