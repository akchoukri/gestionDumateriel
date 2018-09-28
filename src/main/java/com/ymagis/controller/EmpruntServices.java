package com.ymagis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
			if (materiels.get(i).getDisponible() == true && materiels.get(i).getEtatMateriel().equals("bonne etat")) {
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
		if(client.get()==null)throw new  RuntimeException("N'existe pas un client avec ce nom , Veuillez réessayer ");
		if(emprunt.getMateriels()==null || emprunt.getPrixTotal()==0 || emprunt.getDateEmprunt()==null ||  emprunt.getDateRetourPrevu()==null) 
			throw new  RuntimeException("Vous devez saisir tous les elements d'emprunt avant  l'enregistrement,Veuillez réessayer");
		System.out.println("emprunt:" + emprunt);
		emprunt.setClient(client.get());
		return empruntRepository.save(emprunt);
		// return emprunt;
	}

	// Mise a jour le materiel apres emprunt
	@RequestMapping(value = "/materiel/{id}", method = RequestMethod.PUT)
	public Materiel update(@RequestBody Materiel m, @PathVariable("id") Long id) {
		m.setIdMateriel(id);
		return materielRepository.save(m);

	}

	@RequestMapping(value = "/allEmprunts", method = RequestMethod.GET)
	public List<Emprunt> liste() {
		return empruntRepository.findAll();
	}

	// mettre a jour client
	@RequestMapping(value = "/client/{id}/emprunts", method = RequestMethod.PUT)
	public Emprunt updateClient(@PathVariable Long id, @RequestBody Emprunt emprunt) {
		Optional<Client> client = clientRepository.findById(id);
		emprunt.setClient(client.get());
		materielRepository.saveAll(emprunt.getMateriels());
		empruntRepository.save(emprunt);
		return emprunt;
	}

	// recuperer les emprunts en retard d'un clients
	@RequestMapping(value = "/client/{id}/nnretourne", method = RequestMethod.GET)
	public List<Emprunt> getRetardByClient(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		List<Emprunt> empR = new ArrayList<>();
		if (client.get().getEmprunts() != null) {

			for (Emprunt emprunt : client.get().getEmprunts()) {
				if (emprunt.getDateRetour() == null)// emprunt nn retourné
					empR.add(emprunt);
			}
		}

		return empR;

	}

	// mettre a jour des materiel
	@RequestMapping(value = "/materiels", method = RequestMethod.PUT)
	public boolean updateMateriels(@RequestBody List<Materiel> materiel) {
		materielRepository.saveAll(materiel);
		return true;
	}

	// recuperer les emprunts en retard
	@RequestMapping(value = "/empruntRetard", method = RequestMethod.GET)
	public List<Emprunt> getEmpruntRetard() throws ParseException {
		List<Emprunt> emprunts = empruntRepository.findAll();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(date);
		date = formatter.parse(s);

		List<Emprunt> empR = new ArrayList<>();
		for (Emprunt emprunt : emprunts) {
			if ((emprunt.getDateRetour() != null)
					&& (diffDate(emprunt.getDateRetour(), emprunt.getDateRetourPrevu()))) {
				empR.add(emprunt);
			} else if ((emprunt.getDateRetour() == null) && (diffDate(date, emprunt.getDateRetourPrevu()))) {
				empR.add(emprunt);
			}
		}
		return empR;
	}

	//
	public boolean diffDate(Date date1, Date date2) {
		System.out.println(date1 + " / " + date2);

		if (date1.getTime() > date2.getTime())
			return true;
		return false;
	}
}
