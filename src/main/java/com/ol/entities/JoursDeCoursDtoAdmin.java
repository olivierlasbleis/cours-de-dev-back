package com.ol.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoursDeCoursDtoAdmin{

	String jourDeLaSemaine;
	String date;
	List<HeureDeCoursDtoAdmin> listeHeureDeCoursDtoAdmin;
}
