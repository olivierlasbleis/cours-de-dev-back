package com.ol.entities;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JourDeCours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate localDateDuJour;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "JourDeCours_id")
	List<HeureDeCours> listeHeuresDeCours;
	
	public JourDeCours(LocalDate localDate) {
		super();
		this.localDateDuJour = localDate;
		if (localDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
			this.listeHeuresDeCours = Arrays.asList(
					new HeureDeCours(localDate, LocalTime.of(9, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.INDISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(10, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(11, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(12, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(13, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(14, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(15, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(16, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(17, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(18, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(19, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500));
		}else {
			this.listeHeuresDeCours = Arrays.asList(
					new HeureDeCours(localDate, LocalTime.of(9, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.INDISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(10, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.INDISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(11, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.INDISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(12, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.INDISPONIBLE,2500),
					new HeureDeCours(localDate, LocalTime.of(13, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(14, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_COURS,2500),
					new HeureDeCours(localDate, LocalTime.of(15, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_COURS,2500),
					new HeureDeCours(localDate, LocalTime.of(16, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_COURS,2500),
					new HeureDeCours(localDate, LocalTime.of(17, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(18, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.DISPONIBLE_CONSULTATION,2500),
					new HeureDeCours(localDate, LocalTime.of(19, 0, 0, 0),Duration.ofMinutes(50),StatutHeure.INDISPONIBLE,2500));
		}
	}
	
	

}
