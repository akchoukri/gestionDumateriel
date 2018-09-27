package com.ymagis.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ymagis.model.Materiel;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel, Long> {

	public Materiel getMaterielByIdMateriel(Long idMateriel);

	@Query("select m from Materiel m where m.designation like :x")
	public Page<Materiel> chercherMateriel(@Param("x") String mc, Pageable pageable);

	public List<Materiel> findByDesignation(String mc);

	// recuperer les nouveaux materiel ajouter dans un mois
	@Query("select mat from Materiel mat where   EXTRACT(MONTH FROM  mat.dateAjoutMateriel) = :m AND EXTRACT(YEAR FROM mat.dateAjoutMateriel) = :y ")
	public List<Materiel> getNvMatByMonth(@Param("y") int year, @Param("m") int month);

}
