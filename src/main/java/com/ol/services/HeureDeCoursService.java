package com.ol.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.entities.Client;
import com.ol.entities.HeureDeCours;
import com.ol.entities.HeureDeCoursDto;
import com.ol.entities.HeureDeCoursDtoAdmin;
import com.ol.entities.JourDeCours;
import com.ol.entities.StatutHeure;
import com.ol.exceptions.HeureConsultationIndisponibleException;
import com.ol.exceptions.HeureDeCoursIndisponibleException;
import com.ol.exceptions.HeureIndisponibleException;
import com.ol.repositories.HeureDeCoursRepository;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

@Service
public class HeureDeCoursService {
	
	@Autowired
	HeureDeCoursRepository heureDeCoursRepository;
	
	public HeureDeCours reserverLeCours(HeureDeCours heureDeCours, Client client) throws HeureDeCoursIndisponibleException{
		if (heureDeCours.getStatut() == StatutHeure.DISPONIBLE) {
			heureDeCours.setStatut(StatutHeure.INDISPONIBLE);
			heureDeCours.setClient(client);
		}else {
			throw new HeureDeCoursIndisponibleException();
		}
		return heureDeCoursRepository.save(heureDeCours);
	}
	
	public HeureDeCours convertFromDtoToDBO(HeureDeCoursDto heureDeCoursDto){
		return heureDeCoursRepository.findById(heureDeCoursDto.getId()).get();
	}
	
	public HeureDeCoursDto convertFromDBOToDto(HeureDeCours heureDeCoursDBO){
		return new HeureDeCoursDto(heureDeCoursDBO.getId(),
				heureDeCoursDBO.getLocalDato().toString(),
				heureDeCoursDBO.getLocalTimo().toString(),
				heureDeCoursDBO.getStatut().toString(),
				heureDeCoursDBO.getPrix());
	}
	
	public HeureDeCoursDtoAdmin convertFromDBOToDtoAdmin(HeureDeCours heureDeCoursDBO){
		return new HeureDeCoursDtoAdmin(heureDeCoursDBO.getId(),
				heureDeCoursDBO.getLocalDato().toString(),
				heureDeCoursDBO.getLocalTimo().toString(),
				heureDeCoursDBO.getStatut().toString(),
				heureDeCoursDBO.getPrix(),
				heureDeCoursDBO.getClient());
	}
	
	public List<String> getHeuresDisponiblesPourConsultation(LocalDate localDate) throws HeureConsultationIndisponibleException {
		
		List<HeureDeCours> listeHeures = new ArrayList<>();
		if (heureDeCoursRepository.findByDateHeuresAndStatut(localDate, StatutHeure.DISPONIBLE_CONSULTATION).isPresent()) {
			listeHeures = heureDeCoursRepository.findByDateHeuresAndStatut(localDate, StatutHeure.DISPONIBLE_CONSULTATION).get();
		}else {
			throw new HeureConsultationIndisponibleException();
		}
				
		return listeHeures.stream().map(h -> h.getLocalTimo().getHour() + "h").collect(Collectors.toList());
	}

	public List<HeureDeCours> getHeuresByDate(LocalDate localDate) throws HeureIndisponibleException {
		List<HeureDeCours> listeHeures = new ArrayList<>();
		if (heureDeCoursRepository.findByLocalDato(localDate).isPresent()) {
			listeHeures = heureDeCoursRepository.findByLocalDato(localDate).get();
		}else {
			throw new HeureIndisponibleException();
		}
				
		return listeHeures;
	}

	public HeureDeCours save(HeureDeCours heureDeCours) {
		return heureDeCoursRepository.save(heureDeCours);
	}

	public HeureDeCours modifyHeure(HeureDeCours heureDeCours) throws HeureIndisponibleException {
		if (heureDeCoursRepository.findById(heureDeCours.getId()).isPresent()) {
			return heureDeCoursRepository.save(heureDeCours);
		}else {
			throw new HeureIndisponibleException("aucune heure avec l'id : " + heureDeCours.getId());
		}
	}

	public HeureDeCours deleteHeureById(Integer idHeure) throws HeureIndisponibleException {
		if (heureDeCoursRepository.findById(idHeure).isPresent()) {
			HeureDeCours heureDeCours = heureDeCoursRepository.findById(idHeure).get();
			 heureDeCoursRepository.delete(heureDeCours);
			 return heureDeCours;
		}else {
			throw new HeureIndisponibleException("aucune heure avec l'id : " + idHeure);
		}
	}

	public List<HeureDeCours> addListeHeureDeCours(List<HeureDeCours> listeHeureDeCours) {
		List<HeureDeCours> listeHeureDeCoursRetour = new ArrayList<>();
		listeHeureDeCours.stream().forEach(a ->listeHeureDeCoursRetour.add(save(a)));
		return listeHeureDeCoursRetour;
	}
}
