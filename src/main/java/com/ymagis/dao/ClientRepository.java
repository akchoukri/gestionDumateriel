package com.ymagis.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ymagis.model.Client;
import com.ymagis.model.Emprunt;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select c from Client c where c.nomClient like :x and c.isArchive = false ORDER BY c.idClient ASC")
	public Page<Client> getClientByNom(@Param("x")String mc, Pageable pageable);
	
	@Query("select c from Client c where c.nomClient like :x")
	public List<Client> chercherClient(@Param("x") String mc);
	
	@Query("select c from Client c where  c.isArchive = false ORDER BY c.idClient ASC")
	public List<Client> getClients();
	
	@Query("select c from Client c where   EXTRACT(MONTH FROM  c.dateAjoutClient) = :m AND EXTRACT(YEAR FROM c.dateAjoutClient) = :y ")
	public List<Client> getNvClientByMonth(@Param("y") int year,@Param("m")int month);
}
