package com.ymagis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymagis.dao.ClientRepository;
import com.ymagis.model.Client;

@RestController
@CrossOrigin("*")
public class ClientController {

	@Autowired

	private ClientRepository clientRepository;

	// ajouter un client

	@RequestMapping(method = RequestMethod.POST, value = "/clients/add")

	public Client saveClient(@RequestBody Client client) {

		clientRepository.save(client);

		return client;

	}

	// recuperer un client by id

	@RequestMapping(method = RequestMethod.GET, value = "/clients/{id}")

	public Client getClient(@PathVariable Long id) {

		Client client = clientRepository.findById(id).get();

		return client;

	}

	// supprimer un client

	@RequestMapping(method = RequestMethod.DELETE, value = "/clients/{id}")

	public String deleteClient(@PathVariable Long id) {

		Optional<Client> client = clientRepository.findById(id);

		if (client.get() != null) {

			clientRepository.delete(client.get());

			return "le client" + client.get().getNomClient() + " est supprimé avec succes";

		}

		return "error pour suppression";

	}

	// mettre a jour client

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)

	public Client updateClient(@PathVariable Long id, @RequestBody Client client) {

		clientRepository.save(client);

		return client;

	}

	// recuperer la liste des clients

	@RequestMapping(value = "/listClients", method = RequestMethod.GET)

	public List<Client> getClients() {

		return clientRepository.getClients();

	}

	// recuperer des clients pageable

	@RequestMapping(value = "/clients", method = RequestMethod.GET)

	public Page<Client> chercher(@RequestParam(name = "mc", defaultValue = "") String mc,

			@RequestParam(name = "page", defaultValue = "0") int page,

			@RequestParam(name = "size", defaultValue = "2") int size) {

		return clientRepository.getClientByNom("%" + mc + "%", PageRequest.of(page, size));

	}

}
