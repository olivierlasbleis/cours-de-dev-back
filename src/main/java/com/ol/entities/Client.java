package com.ol.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String numeroTelephone;
	private LocalDate localDateInsertionDsBDD;
	
	public Client() {
		super();
		this.localDateInsertionDsBDD = LocalDate.now();
	}
	
	public Client(String nom, String prenom, String email, String numeroTelephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numeroTelephone = numeroTelephone;
	}
	

}
