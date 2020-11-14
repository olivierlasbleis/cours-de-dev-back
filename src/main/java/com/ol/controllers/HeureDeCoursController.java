package com.ol.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.ClientEndpoint;

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
import com.ol.entities.DateDto;
import com.ol.entities.HeureDeCours;
import com.ol.entities.JourDeCours;
import com.ol.exceptions.ClientIndisponibleException;
import com.ol.exceptions.HeureConsultationIndisponibleException;
import com.ol.exceptions.HeureIndisponibleException;
import com.ol.services.ClientService;
import com.ol.services.HeureDeCoursService;

@RestController
@RequestMapping("heureDeCours")
public class HeureDeCoursController {

	@Autowired
	HeureDeCoursService heuresDeCoursServives;
	
	@Autowired
	ClientService clientService;
	
	@PostMapping("/getHeuresDisponiblesPourConsultation")
	public List<String> getHeuresDisponiblesPourConsultation(@RequestBody DateDto dateDto) throws HeureConsultationIndisponibleException {
		return heuresDeCoursServives.getHeuresDisponiblesPourConsultation(LocalDate.of(dateDto.getYear(), dateDto.getMonth(), dateDto.getDay()));
	}
	
	@PostMapping("/getHeuresByDate")
	public List<HeureDeCours> getHeuresByDate(@RequestBody DateDto dateDto) throws HeureIndisponibleException {
		return heuresDeCoursServives.getHeuresByDate(LocalDate.of(dateDto.getYear(), dateDto.getMonth(), dateDto.getDay()));
	}
	
	@PostMapping("/newHeure")
	public HeureDeCours newHeure(@RequestBody HeureDeCours heureDeCours) {
		return heuresDeCoursServives.save(heureDeCours);
	}
	
	@PostMapping("/modifyHeure")
	public HeureDeCours modifyClient(@RequestBody HeureDeCours heureDeCours) throws HeureIndisponibleException {
		return heuresDeCoursServives.modifyHeure(heureDeCours);
	}
	
	@GetMapping("/deleteHeureById/{idHeure}")
	public ResponseEntity<String> deleteHeureById(@PathVariable("idHeure") Integer idHeure) throws ClientIndisponibleException, HeureIndisponibleException {
		String heureSupprime = heuresDeCoursServives.deleteHeureById(idHeure).toString();
		return new ResponseEntity<String>(heureSupprime, HttpStatus.OK);
	}
	@PostMapping("/addListeHeure")
	public List<HeureDeCours> addListeHeure(@RequestBody List<HeureDeCours> listeHeureDeCours) {
		return heuresDeCoursServives.addListeHeureDeCours(listeHeureDeCours);
	}
	
}
