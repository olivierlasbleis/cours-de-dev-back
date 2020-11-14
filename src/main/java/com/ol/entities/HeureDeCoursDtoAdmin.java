package com.ol.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeureDeCoursDtoAdmin extends HeureDeCoursDto{

	private Client client;
	
	
	
	public HeureDeCoursDtoAdmin(Integer id, String dateJour, String dateHeure, String etat, Integer prix, Client client) {
		super(id, dateJour, dateHeure, etat, prix);
		this.client = client;
		
	}
}
