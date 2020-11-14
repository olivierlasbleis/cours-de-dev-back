package com.ol.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecapitulatifCommandeDto {

	private List<HeureDeCoursDto> listeHeuresDeCoursDto;
	private List<String> listeDescriptionCommande;
	private int prix;

}
