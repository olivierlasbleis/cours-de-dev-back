package com.ol.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeureDeCoursDto {

	Integer id;
	String dateJour;
	String dateHeure;
	String etat;
	Integer prix;
	
}
