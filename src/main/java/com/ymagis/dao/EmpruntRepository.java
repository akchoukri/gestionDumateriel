package com.ymagis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ymagis.model.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	Emprunt findByIdEmpruntLike(Long idEmprunt);

	// recuperer les emprunt retourné en precisant le mois et l'année
	@Query("select c from Emprunt c where   EXTRACT(MONTH FROM  c.dateRetour) = :m AND EXTRACT(YEAR FROM c.dateRetour) = :y ")
	public List<Emprunt> getEmpruntRetour(@Param("y") int year, @Param("m") int month);

	// recuperer les emprunt retard en precisant le mois et l'année
	@Query("select c from Emprunt c where c.dateRetour = null AND current_date > c.dateRetourPrevu ")
	public List<Emprunt> getEmpruntRetard();

	// recuperer les emprunt d'un client en precisant le mois et l'année
	@Query("select c from Emprunt c where   EXTRACT(MONTH FROM  c.dateEmprunt) = :m AND EXTRACT(YEAR FROM c.dateEmprunt) = :y AND c.client.idClient = :d ")
	public List<Emprunt> getEmpruntsClientByMonth(@Param("y") int year, @Param("m") int month, @Param("d") Long id);

	// recuperer les emprunt en precisant le mois et l'année
	@Query("select c from Emprunt c where   EXTRACT(MONTH FROM  c.dateEmprunt) = :m AND EXTRACT(YEAR FROM c.dateEmprunt) = :y")
	public List<Emprunt> getEmpruntsByMonth(@Param("y") int year, @Param("m") int month);
}
