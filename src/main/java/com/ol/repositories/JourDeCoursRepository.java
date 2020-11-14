package com.ol.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.entities.JourDeCours;
import com.ol.entities.JourDeCoursDto;

@Repository
public interface JourDeCoursRepository extends JpaRepository<JourDeCours, Integer>{

	Optional<JourDeCours> findByLocalDateDuJour(LocalDate localDateDuJour);
 
	
	@Query(value = "SELECT * FROM JOUR_DE_COURS WHERE LOCAL_DATE_DU_JOUR BETWEEN :dateDebut AND :dateFin", 
			  nativeQuery = true)
	Optional<List<JourDeCours>> findByDates( 
			@Param("dateDebut") LocalDate dateDebut, 
			@Param("dateFin") LocalDate dateFin);
}
