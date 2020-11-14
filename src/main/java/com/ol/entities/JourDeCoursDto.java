package com.ol.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourDeCoursDto {
	String jourDeLaSemaine;
	String date;
	List<HeureDeCoursDto> listeHeureDeCoursDto;
}
