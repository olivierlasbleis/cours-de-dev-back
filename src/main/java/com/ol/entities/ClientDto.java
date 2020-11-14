package com.ol.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

	private String nom;
	private String prenom;
	private String email;
	private String numeroTelephone;
	
}
