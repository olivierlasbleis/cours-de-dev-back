package com.ol.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.entities.Client;
import com.ol.entities.HeureDeCours;
import com.ol.entities.JourDeCours;
import com.ol.entities.StatutHeure;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	Optional<Client> findByNomAndPrenomAndEmailAndNumeroTelephone(String nom, String prenom, String email, String numeroTelephone);

	@Query(value = "SELECT * FROM CLIENT WHERE ID = (SELECT CLIENT_ID FROM HEURE_DE_COURS WHERE ID = :idHeure)", 
			  nativeQuery = true)
	Optional<Client> findByHeureId(Integer idHeure);

}
