package com.ol.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeureDeCours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate localDato;
	private LocalTime localTimo;
	private Duration durationo;
	private Enum statut;
	private int prix;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	
	public HeureDeCours(LocalDate localDate, LocalTime localtime, Duration duration, Enum statut, int prix) {
		super();
		this.localDato = localDate;
		this.localTimo = localtime;
		this.durationo = duration;
		this.statut = statut;
		this.prix = prix;
	}
}
