package com.ol.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.entities.Client;
import com.ol.entities.HeureDeCours;
import com.ol.entities.HeureDeCoursDto;
import com.ol.entities.HeureDeCoursDtoAdmin;
import com.ol.entities.JourDeCours;
import com.ol.entities.JourDeCoursDto;
import com.ol.entities.JoursDeCoursDtoAdmin;
import com.ol.exceptions.ClientIndisponibleException;
import com.ol.exceptions.JourDeCoursDejaPresentException;
import com.ol.exceptions.JourIndisponibleException;
import com.ol.repositories.JourDeCoursRepository;

@Service
public class JoursDeCoursService {

	@Autowired
	JourDeCoursRepository jourDeCoursRepository;
	
	@Autowired
	HeureDeCoursService heureDeCoursService;
	
	
	public JourDeCours insertNewJourDeCours(JourDeCours joursDeCours) throws JourDeCoursDejaPresentException {
		return saveIfDoesntExist(joursDeCours);
	}
	
	public JourDeCours saveIfDoesntExist(JourDeCours joursDeCours) throws JourDeCoursDejaPresentException {
		if (jourDeCoursRepository.findByLocalDateDuJour(joursDeCours.getLocalDateDuJour()).isPresent()) {
			return jourDeCoursRepository.findByLocalDateDuJour(joursDeCours.getLocalDateDuJour()).get();
		}else {
			return jourDeCoursRepository.save(joursDeCours);
		}
	}

	public List<JourDeCoursDto> findNextJoursDeCoursDisponibles() {
		List<JourDeCours> listeHDCDTO = jourDeCoursRepository.findByDates(LocalDate.now(), LocalDate.now().plusDays(7)).get();
		
		return (List<JourDeCoursDto>) jourDeCoursRepository.findByDates(LocalDate.now(), LocalDate.now().plusDays(7)).get().stream().map(
				j -> convertFromDBOtoDto(j)).collect(Collectors.toList());
	}
	
	public JourDeCoursDto convertFromDBOtoDto(JourDeCours jourDeCours) {
		
		List<HeureDeCoursDto> listeHeuresDeCoursDto = jourDeCours.getListeHeuresDeCours().stream()
		        .map(j -> heureDeCoursService.convertFromDBOToDto(j))
		        .collect(Collectors.toList());
		
		return new JourDeCoursDto(jourDeCours.getLocalDateDuJour().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH).substring(0, 4),
				jourDeCours.getLocalDateDuJour().format(DateTimeFormatter.ofPattern("dd MMM", Locale.FRENCH)),
				listeHeuresDeCoursDto);
	}
	
	public JoursDeCoursDtoAdmin convertFromDBOtoDtoAdmin(JourDeCours jourDeCours) {
		
		List<HeureDeCoursDtoAdmin> listeHeuresDeCoursDtoAdmin = jourDeCours.getListeHeuresDeCours().stream()
		        .map(j -> heureDeCoursService.convertFromDBOToDtoAdmin(j))
		        .collect(Collectors.toList());
		
		return new JoursDeCoursDtoAdmin(jourDeCours.getLocalDateDuJour().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH),
				jourDeCours.getLocalDateDuJour().format(DateTimeFormatter.ofPattern("dd MMM", Locale.FRENCH)),
				listeHeuresDeCoursDtoAdmin);
	}

	public List<JoursDeCoursDtoAdmin> findNextAdmin() {
		List<JourDeCours> listeHDCDTO = jourDeCoursRepository.findByDates(LocalDate.now(), LocalDate.now().plusDays(7)).get();
		
		List<JoursDeCoursDtoAdmin> B = listeHDCDTO.stream()
		        .map(j -> convertFromDBOtoDtoAdmin(j))
		        .collect(Collectors.toList());
		
		return (List<JoursDeCoursDtoAdmin>) jourDeCoursRepository.findByDates(LocalDate.now(), LocalDate.now().plusDays(7)).get().stream().map(
				j -> convertFromDBOtoDtoAdmin(j)).collect(Collectors.toList());
	}

	public JourDeCours newJour(JourDeCours jourDeCours) {
		return jourDeCoursRepository.save(jourDeCours);
	}
	
	public JourDeCours modifyJour(JourDeCours jourDeCours) throws JourIndisponibleException {
		// TODO Auto-generated method stub
		if (jourDeCoursRepository.findById(jourDeCours.getId()).isPresent()) {
			return jourDeCoursRepository.save(jourDeCours);
		}else {
			throw new JourIndisponibleException("aucun jour n'a été trouvé avec l'id : " + jourDeCours.getId());
		}
		
	}

	public JourDeCours deleteJourById(Integer idJour) throws JourIndisponibleException {
		if (jourDeCoursRepository.findById(idJour).isPresent()) {
			JourDeCours jourDeCours = jourDeCoursRepository.findById(idJour).get();
			jourDeCoursRepository.delete(jourDeCours);
			return jourDeCours;
		}else {
			throw new JourIndisponibleException("aucun jour n'a été trouvé avec l'id : " + idJour);
		}
	}

	public List<JourDeCours> addListeJour(List<JourDeCours> listeJourDeCours) {
		List<JourDeCours> listeJourDeCoursRetour = new ArrayList<>();
		listeJourDeCours.stream().forEach(a ->listeJourDeCoursRetour.add(a));
		return listeJourDeCoursRetour;
	}
	
	
	
}
