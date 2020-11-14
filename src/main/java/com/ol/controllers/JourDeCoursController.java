package com.ol.controllers;

import java.time.LocalDate;
import java.util.Date;
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

import com.ol.entities.CommandeDto;
import com.ol.entities.DateDto;
import com.ol.entities.HeureDeCours;
import com.ol.entities.JourDeCours;
import com.ol.entities.JourDeCoursDto;
import com.ol.entities.JoursDeCoursDtoAdmin;
import com.ol.exceptions.ClientIndisponibleException;
import com.ol.exceptions.HeureConsultationIndisponibleException;
import com.ol.exceptions.HeureIndisponibleException;
import com.ol.exceptions.JourIndisponibleException;
import com.ol.repositories.JourDeCoursRepository;
import com.ol.services.HeureDeCoursService;
import com.ol.services.JoursDeCoursService;

@RestController
@RequestMapping("jourDeCours")
public class JourDeCoursController {

	@Autowired
	JoursDeCoursService joursDeCoursSerives;
	@Autowired
	HeureDeCoursService heuresDeCoursSerives;
	
	@GetMapping("/next")
	public List<JourDeCoursDto> getNextJoursDeCoursDisponibles() {
		return joursDeCoursSerives.findNextJoursDeCoursDisponibles();
	}
	
	
	
	@GetMapping("/admin/next")
	public List<JoursDeCoursDtoAdmin> getNextJoursDeCoursAdmin() {
		return joursDeCoursSerives.findNextAdmin();
	}
	
	@PostMapping("/newJour")
	public JourDeCours newJour(@RequestBody JourDeCours jourDeCours) {
		return joursDeCoursSerives.newJour(jourDeCours);
	}
	
	@PostMapping("/addListeJour")
	public List<JourDeCours> addListeJour(@RequestBody List<JourDeCours> listeJourDeCours) {
		return joursDeCoursSerives.addListeJour(listeJourDeCours);
	}
	
	@PostMapping("/modifyJour")
	public JourDeCours modifyJour(@RequestBody JourDeCours jourDeCours) throws JourIndisponibleException {
		return joursDeCoursSerives.modifyJour(jourDeCours);
	}
	
	@GetMapping("/deleteJourById/{idJour}")
	public ResponseEntity<String> deleteJourById(@PathVariable("idJour") Integer idJour) throws JourIndisponibleException {
		String jourSupprime = joursDeCoursSerives.deleteJourById(idJour).toString();
		return new ResponseEntity<String>(jourSupprime, HttpStatus.OK);
	}
}
