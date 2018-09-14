package com.ymagis.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymagis.dao.CategorieRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Categorie;
import com.ymagis.model.Materiel;
import com.ymagis.pojo.MaterielPojo;

@RestController
public class MaterielRestService {
	@Autowired
	MaterielRepository materielRepository;
	@Autowired
	CategorieRepository categorieRepository;

	// consulter la liste des materiels
	@RequestMapping(value = "/materiels", method = RequestMethod.GET)
	public List<Materiel> listMateriel() {
		return materielRepository.findAll();
	}
	
	// consulter la liste des catégories
		@RequestMapping(value = "/categories", method = RequestMethod.GET)
		public List<Categorie> listCategorie() {
			return categorieRepository.findAll();
		}

	// consulter la liste des materiels par page
	@RequestMapping(value = "/materiel", method = RequestMethod.GET)
	public Page<Materiel> listMateriel(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageRequest = new PageRequest(page, size);
		return materielRepository.findAll(pageRequest);
	}

	// recherche par mot clé
	@RequestMapping(value = "/cherchermateriels", method = RequestMethod.GET)
	public Page<Materiel> chercher(@RequestParam(name = "mc", defaultValue = "") String mc,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return materielRepository.chercherMateriel("%"+ mc +"%", PageRequest.of(page, size));
	}

	// consulter un seul materiel
	@RequestMapping(value = "/materiel/{id}", method = RequestMethod.GET)
	public Materiel getMateriel(@PathVariable("id") Long id) {
		return materielRepository.getMaterielByIdMateriel(id);
	}

	// Ajouter un materiel
	@RequestMapping(value = "/materiel", method = RequestMethod.POST)
	public Materiel save(@RequestBody MaterielPojo m) {
		//m.setDateAjoutMateriel(new Date());

		Materiel materiel = materielToPojo(m);
		String nomCategorie = m.getNomCategorie();
		Categorie categorie = categorieRepository.findByNomCategorie(nomCategorie);
		materiel.setCategorie(categorie);
		if(materiel.getDesignation()==null || materiel.getReference()==null ||materiel.getEtatMateriel()==null){
			return null;
		}
		else{
			return materielRepository.save(materiel);

		}
	}

	// modification d un materiel
	@RequestMapping(value = "/materiel/{id}", method = RequestMethod.PUT)
	public Materiel update(@RequestBody MaterielPojo m, @PathVariable Long id) {
		Materiel materiel = materielToPojo(m);
		materiel.setIdMateriel(id);
		String nomCategorie = m.getNomCategorie();
		Categorie categorie = categorieRepository.findByNomCategorie(nomCategorie);
		materiel.setCategorie(categorie);
		return materielRepository.save(materiel);
	}
	
	private Materiel materielToPojo(MaterielPojo m) {
		Materiel materiel = new Materiel();
		materiel.setReference(m.getReference());
		materiel.setDesignation(m.getDesignation());
		materiel.setDateAjoutMateriel(new Date());
		materiel.setQuantite(m.getQuantite());
		materiel.setEtatMateriel(m.getEtatMateriel());
		materiel.setDisponible(m.isDisponible());
		
		return materiel;
	}


	// suppression d un materiel
	@RequestMapping(value = "/materiel/{id}", method = RequestMethod.DELETE)
	public void deleteMateriel(@PathVariable("id") Long id) {
		materielRepository.deleteById(id);
	}
}
