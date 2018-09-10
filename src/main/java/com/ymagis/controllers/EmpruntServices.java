package com.ymagis.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ymagis.dao.ClientRepository;
import com.ymagis.dao.EmpruntRepository;
import com.ymagis.dao.MaterielRepository;
import com.ymagis.model.Client;
import com.ymagis.model.Emprunt;
import com.ymagis.model.Materiel;

@RestController
@CrossOrigin("*")
public class EmpruntServices {
	// Injection de depandance
	@Autowired
	private EmpruntRepository empruntRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private MaterielRepository materielRepository;

	@RequestMapping(value = "/allEmprunts", method = RequestMethod.GET)
	public List<Emprunt> liste() {
		return empruntRepository.findAll();
	}

	// Ajouter un nouveau emprunt
	public void idClient(Emprunt emprunt, Client client) {
		emprunt.setClient(client);
		empruntRepository.save(emprunt);
	}

	// recuperer la liste des clients
	@GetMapping(value = "/chercherClients")
	public List<Client> getAllClients() {
		List<Client> clients = this.clientRepository.findAll();
		return clients;
	}

	// recuperer la liste des materiels
	@GetMapping(value = "/chercherMateriels")
	public List<Materiel> getAllMateriels() {
		List<Materiel> materiels = this.materielRepository.findAll();
		List<Materiel> materielsDisponible = new ArrayList<>();
		for (int i = 0; i < materiels.size(); i++) {
			if (materiels.get(i).getDisponible() == true && materiels.get(i).getEtatMateriel().equals("bonne")) {
				materielsDisponible.add(materiels.get(i));
			}
		}
		System.out.println(materielsDisponible);
		return materielsDisponible;
	}

	// chercher les clients
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public List<Client> chercher(@RequestParam(name = "mc", defaultValue = "") String mc) {
		return clientRepository.chercherClient("%" + mc + "%");
	}

	// chercher les materiels
	@RequestMapping(value = "/materiel", method = RequestMethod.GET)
	public List<Materiel> findMateriel(@RequestParam(name = "mc", defaultValue = "") String mc) {
		List<Materiel> materielsChoisis = this.materielRepository.findByDesignation(mc);
		List<Materiel> materielsDisponible = new ArrayList<>();
		for (int i = 0; i < materielsChoisis.size(); i++) {
			if (materielsChoisis.get(i).getDisponible() == true) {
				materielsDisponible.add(materielsChoisis.get(i));
			}
		}
		// return materielRepository.chercherMateriel("%" + mc + "%");
		return materielsDisponible;
	}
	

	// ajouter un nouveau emprunt
	@PostMapping("/client/{idClient}/emprunts")
	public Emprunt createEmprunt(@PathVariable(value = "idClient") Long idClient, @RequestBody Emprunt emprunt)
			throws ParseException {
		Optional<Client> client = clientRepository.findById(idClient);
		System.out.println("emprunt:" + emprunt);
		emprunt.setClient(client.get());
		System.out.println(emprunt.getMateriels());
		return empruntRepository.save(emprunt);
		// return emprunt;
	}


	// Mise a jour le materiel apres emprunt
	@RequestMapping(value = "/materiel/{id}", method = RequestMethod.PUT)
	public Materiel update(@RequestBody Materiel m, @PathVariable("id") Long id) {
		m.setIdMateriel(id);
		return materielRepository.save(m);

	}
}
