package com.ymagis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ymagis.model.Client;
import com.ymagis.model.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	Emprunt findByIdEmpruntLike(Long idEmprunt);
	
	@Query("select c from Emprunt c where   EXTRACT(MONTH FROM TIMESTAMP c.dateRetour) = 9")
	public List<Emprunt> getEmpruntRetour();

}
