package com.ol.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.entities.HeureDeCours;
import com.ol.entities.JourDeCours;
import com.ol.entities.StatutHeure;

public interface HeureDeCoursRepository extends JpaRepository<HeureDeCours, Integer>{

	
	@Query(value = "SELECT * FROM HEURE_DE_COURS WHERE LOCAL_DATO = :dateConcernee and STATUT = :statutHeure", 
			  nativeQuery = true)
	Optional<List<HeureDeCours>> findByDateHeuresAndStatut( 
			@Param("dateConcernee") LocalDate dateConcernee,
			@Param("statutHeure") StatutHeure statutHeure);

	Optional<List<HeureDeCours>> findByLocalDato(LocalDate localDato);
}
