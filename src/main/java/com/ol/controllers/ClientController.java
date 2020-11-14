package com.ol.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.entities.Client;
import com.ol.entities.CommandeDto;
import com.ol.entities.DateDto;
import com.ol.exceptions.ClientIndisponibleException;
import com.ol.exceptions.HeureIndisponibleException;
import com.ol.services.ClientService;
@RestController
@RequestMapping("clients")
public class ClientController {


	@Autowired
	ClientService clientService;
	
	@GetMapping("/getClientByHeureId/{idHeure}")
	public Client getClientByHeureId(@PathVariable("idHeure") Integer idHeure) throws ClientIndisponibleException {
		return clientService.getClientByHeureId(idHeure);
	}
	
	@PostMapping("/newClient")
	public Client newClient(@RequestBody Client client) throws  ClientIndisponibleException {
		return clientService.newClient(client);
	}
	
	@PostMapping("/modifyClient")
	public Client modifyClient(@RequestBody Client client) throws ClientIndisponibleException {
		return clientService.modifyClient(client);
	}
	
	@GetMapping("/deleteClientById/{idClient}")
	public ResponseEntity<String> deleteClientById(@PathVariable("idClient") Integer idClient) throws ClientIndisponibleException {
		String clientSupprime = clientService.deleteClientById(idClient).toString();
		return new ResponseEntity<String>(clientSupprime, HttpStatus.OK);
				
	}
	
	@PostMapping("/addListeClient")
	public List<Client> addListeClient(@RequestBody List<Client> listeClient) {
		return clientService.addListeClient(listeClient);
	}
}
