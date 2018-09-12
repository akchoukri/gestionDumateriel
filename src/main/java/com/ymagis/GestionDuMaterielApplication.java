package com.ymagis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ymagis.dao.CategorieRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Categorie;
import com.ymagis.model.Materiel;

@SpringBootApplication
public class GestionDuMaterielApplication implements CommandLineRunner{

	@Autowired
	private MaterielRepository materielRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDuMaterielApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 
		
		materielRepository.deleteAll();
		categorieRepository.deleteAll();
		
		
		Categorie categorie = new Categorie("cat1");
		Categorie categorie2 = new Categorie("cat2");
		Categorie categorie3 = new Categorie("cat3");
		Categorie categorie4 = new Categorie("cat4");
		Categorie categorie5 = new Categorie("cat5");
		Categorie categorie6 = new Categorie("cat6");
		Categorie categorie7 = new Categorie("cat7");
		
		
//		List<Categorie> listUsers = new ArrayList();
//		
//			listUsers.add(categorie);
//			categorieRepository.saveAll(listUsers);
		
		//categorieRepository.save(categorie);
//		categorieRepository.save(categorie2);
//		categorieRepository.save(categorie3);
//		categorieRepository.save(categorie4);
//		categorieRepository.save(categorie5);
//		categorieRepository.save(categorie6);
//		categorieRepository.save(categorie7);
//		

		
		Materiel materiel  = new Materiel("ref1", "des1", new Date(), true, "bonne etat", 1,categorie);
		Materiel materiel2 = new Materiel("ref2", "des2", new Date(), true, "bonne etat", 1,categorie2);
		Materiel materiel3 = new Materiel("ref3", "des3", new Date(), true, "bonne etat", 1,categorie);
		Materiel materiel4 = new Materiel("ref4", "des4", new Date(), true, "en panne", 1,categorie4);
		Materiel materiel5 = new Materiel("ref5", "des5", new Date(), true, "en panne", 1,categorie5);
		Materiel materiel6 = new Materiel("ref6", "des6", new Date(), true, "bonne etat", 1,categorie6);
		Materiel materiel7 = new Materiel("ref7", "des7", new Date(), true, "bonne etat", 1,categorie7);
		
//
	List<Materiel> listMateriels = new ArrayList();
	listMateriels.add(materiel);
	listMateriels.add(materiel2);
	listMateriels.add(materiel3);
	listMateriels.add(materiel4);
	listMateriels.add(materiel5);
	listMateriels.add(materiel6);
	listMateriels.add(materiel7);
//		materiel.setCategorie(categorie);
//		materiel2.setCategorie(categorie2);
//		materiel3.setCategorie(categorie3);
		
		materielRepository.saveAll(listMateriels);
		//materielRepository.save(materiel2);
		//materielRepository.save(materiel3);
//		materielRepository.save(materiel4);
//		materielRepository.save(materiel5);
//		materielRepository.save(materiel6);
//		materielRepository.save(materiel7);
		
		//materielRepository.save(new Materiel("ref2", "des2", new Date(), true, "bonne etat", 1));
		//materielRepository.save(new Materiel("ref3", "des3", new Date(), true, "bonne etat", 1));
		
		
		
//		List<Materiel> materiels = materielRepository.findAll();
//		materiels.forEach(m -> System.out.println(m.getReference() + " " + m.getCategorie().getNomCategorie()));
		}
}
