package com.ymagis.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ymagis.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	// chercher les emprunts
	@Query("select c from Client c where c.nomClient like :x")
	public List<Client> chercherClient(@Param("x") String mc);

}
