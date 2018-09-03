package com.ymagis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
//	// Ajouter un nouveau emprunt
//	public void idClient(Emprunt emprunt, Client client) {
//		emprunt.setClient(client);
//		empruntRepository.save(emprunt);
//	}
//	//recuperer la liste des clients
//	@GetMapping(value = "/listClients")
//	public List<Client> getAllClients() {
//		List<Client> clients = this.clientRepository.findAll();
//		return clients;
//	}
	//recuperer la liste des materiels
	@GetMapping(value = "/chercherMateriels")
	public List<Materiel> getAllMateriels() {
		List<Materiel> materiels = this.materielRepository.findAll();
		return materiels;
	}
//	//chercher les clients
//	@RequestMapping(value = "/client", method = RequestMethod.GET)
//	public List<Client> chercher(@RequestParam(name = "mc", defaultValue = "") String mc) {
//		return clientRepository.chercherClient("%" + mc + "%");
//	}
	//chercher les materiels
	@RequestMapping(value = "/materiel", method = RequestMethod.GET)
	public List<Materiel> findMateriel(@RequestParam(name = "mc", defaultValue = "") String mc) {
		return materielRepository.chercherMateriel("%" + mc + "%");
	}
	// ajouter un nouveau emprunt
	@PostMapping("/client/{idClient}/emprunts")
	public Emprunt createEmprunt(@PathVariable(value = "idClient") Long idClient, @RequestBody Emprunt emprunt) {
		Optional<Client> client = clientRepository.findById(idClient);
		emprunt.setClient(client.get());
		return empruntRepository.save(emprunt);

	}
	
	  // mettre a jour client
	  @RequestMapping(value="/client/{id}/emprunts",method=RequestMethod.PUT)
	  public Emprunt updateClient(@PathVariable Long id,@RequestBody Emprunt emprunt) { 
			Optional<Client> client = clientRepository.findById(id);
			emprunt.setClient(client.get());
		  empruntRepository.save(emprunt);
		  return emprunt;
	  }
	// recuperer les emprunts en retard d'un clients
	@RequestMapping(value = "/client/{id}/nnretourne", method = RequestMethod.GET)
	public List<Emprunt> getRetardByClient(@PathVariable Long id ) {
		Optional<Client> client = clientRepository.findById(id);
		List<Emprunt> empR = new ArrayList<>();
		if (client.get().getEmprunts()!=null) {
			
			for(Emprunt emprunt:client.get().getEmprunts()) {
				if(emprunt.getDateRetour()==null)//emprunt nn retourné
						empR.add(emprunt);
			}
		}


		return empR;

	}
	//recuperer les emprunts en retard
	@RequestMapping(value = "/empruntRetard", method = RequestMethod.GET)
	public List<Emprunt> getEmpruntRetard() {
		List<Emprunt> emprunts = empruntRepository.findAll();
		Date date = new Date();
		List<Emprunt> empR = new ArrayList<>();
			for(Emprunt emprunt:emprunts) {
				if((emprunt.getDateRetour()!=null)&&
						(diffDate(emprunt.getDateRetour(),emprunt.getDateRetourPrevu())))
						{
							empR.add(emprunt);
						}
				else if((emprunt.getDateRetour()==null)&&
						(diffDate(date,emprunt.getDateRetourPrevu())))
				{
					empR.add(emprunt);
				}
			}
		return empR;
	}
	//
	public boolean diffDate(Date date1,Date date2) {
		if(date1.getTime()>date2.getTime())return true;
		return  false;
	}
}
