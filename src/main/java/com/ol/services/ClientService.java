package com.ol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.entities.Client;
import com.ol.entities.ClientDto;
import com.ol.entities.HeureDeCours;
import com.ol.exceptions.ClientIndisponibleException;
import com.ol.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	public Client convertFromDtoToDBO(ClientDto clientDto) {
		if (findClientByNomAndPrenomAndEmailAndNumeroTelephone(clientDto).isPresent()) {
			return findClientByNomAndPrenomAndEmailAndNumeroTelephone(clientDto).get();
		}else {
			return clientRepository.save(new Client(
					clientDto.getNom(),
					clientDto.getPrenom(),
					clientDto.getEmail(),
					clientDto.getNumeroTelephone()));
		}
	}
	
	public ClientDto convertFromDBOToDto(Client client) {
		
			return new ClientDto(client.getNom(),
					client.getPrenom(),
					client.getEmail(),
					client.getNumeroTelephone());
		
	}
	
	public Optional<Client> findClientByNomAndPrenomAndEmailAndNumeroTelephone(ClientDto clientDto){
		return clientRepository.findByNomAndPrenomAndEmailAndNumeroTelephone(
				clientDto.getNom(),
				clientDto.getPrenom(),
				clientDto.getEmail(),
				clientDto.getNumeroTelephone());
		
	}

	public Client getClientByHeureId(Integer idHeure) throws ClientIndisponibleException {
		if (clientRepository.findByHeureId(idHeure).isPresent()) {
			return clientRepository.findByHeureId(idHeure).get();
		}else {
			throw new ClientIndisponibleException("aucun client pour cette heure");
		}
	}

	public Client newClient(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.save(client);
	}

	public Client modifyClient(Client client) throws ClientIndisponibleException {
		// TODO Auto-generated method stub
		if (clientRepository.findById(client.getId()).isPresent()) {
			return clientRepository.save(client);
		}else {
			throw new ClientIndisponibleException("aucun client n'a été trouvé avec l'id : " + client.getId());
		}
		
	}

	public Client deleteClientById(Integer idClient) throws ClientIndisponibleException {
		if (clientRepository.findById(idClient).isPresent()) {
			Client client = clientRepository.findById(idClient).get();
			clientRepository.delete(client);
			return client;
		}else {
			throw new ClientIndisponibleException("aucun client n'a été trouvé avec l'id : " + idClient);
		}
	}
	public List<Client> addListeClient(List<Client> listeClient) {
		List<Client> listeClientRetour = new ArrayList<>();
		listeClient.stream().forEach(a ->listeClientRetour.add(a));
		return listeClientRetour;
	}
	
}
