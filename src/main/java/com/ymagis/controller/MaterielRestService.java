package com.ymagis.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ymagis.utils.Constantes;
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
import com.ymagis.model.Client;
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
	@RequestMapping(value = "/materielss", method = RequestMethod.GET)
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
		Materiel materiel = materielToPojo(m);
		Categorie categorie = m.getCategorie();
		materiel.setCategorie(m.getCategorie());
		Materiel matExist = materielRepository.getMatByDesignAndRef(materiel.getDesignation(), materiel.getReference());
		if(materiel.getReference()==null || materiel.getDesignation() == null || materiel.getCategorie() == null)
			throw new RuntimeException("vous devez remprlir ts les champs");
	   if(matExist != null) throw new RuntimeException("ref est deja prise");
		materielRepository.save(materiel);
	return materiel;
	}

	// modification d un materiel
	@RequestMapping(value = "/materials/{id}", method = RequestMethod.PUT)
	public Materiel update(@RequestBody MaterielPojo m, @PathVariable Long id) {
		Materiel materiel = materielToPojo(m);
		materiel.setIdMateriel(id);
		String nomCategorie = m.getCategorie().getNomCategorie();
		Optional<Categorie> categorie = categorieRepository.findByNomCategorie(nomCategorie);
		materiel.setCategorie(categorie.get());
		Materiel matExist = materielRepository.getMatByDesignAndRef(materiel.getDesignation(), materiel.getReference());
		if(materiel.getReference()==null || materiel.getDesignation() == null || materiel.getCategorie() == null)
			throw new RuntimeException("vous devez remprlir ts les champs");
	   if(matExist != null) throw new RuntimeException("ref est deja prise");
		return materielRepository.save(materiel);
	}

	//Créer un nouveau objet materiel via la classe materielPojo
	private Materiel materielToPojo(MaterielPojo m) {
		Materiel materiel = new Materiel();
		materiel.setReference(m.getReference());
		materiel.setDesignation(m.getDesignation());
		//Cas d'un nouveau materiel
		if(null == m.getIdMateriel()){
			materiel.setEtatMateriel(Constantes.BONNE_ETAT);
			materiel.setDisponible(true);
			materiel.setDateAjoutMateriel(new Date());
		}else{
			//Cas d'un materiel exist deja
			materiel.setEtatMateriel(m.getEtatMateriel());
			materiel.setDisponible(m.isDisponible());
			materiel.setDateAjoutMateriel(m.getDateAjoutMateriel());
		}
		return materiel;
	}


	// suppression d un materiel
	@RequestMapping(value = "/materiel/{id}", method = RequestMethod.DELETE)
	public void deleteMateriel(@PathVariable("id") Long id) {
		materielRepository.deleteById(id);
	}
	
	// recuperer la designations
	@RequestMapping(value = "/designations", method = RequestMethod.GET)
	public List<String> getClients() {
		return materielRepository.getMatDesignation();
	}
	
	// Ajouter un categorie
	@RequestMapping(value = "/categorie", method = RequestMethod.POST)
	public Categorie saveCat(@RequestBody Categorie categorie) {
		Optional<Categorie> catExist = categorieRepository.findByNomCategorie(categorie.getNomCategorie());
		if(catExist.isPresent())throw new RuntimeException("categorie deja existe");
		if(!categorie.getNomCategorie().equals(""))
			categorieRepository.save(categorie);
	return categorie;
	}

}
