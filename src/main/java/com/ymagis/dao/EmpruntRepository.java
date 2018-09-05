package com.ymagis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ymagis.model.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	Emprunt findByIdEmpruntLike(Long idEmprunt);

}
