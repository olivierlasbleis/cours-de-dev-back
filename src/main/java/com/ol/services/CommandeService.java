package com.ol.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.ol.entities.Client;
import com.ol.entities.CommandeDto;
import com.ol.entities.HeureDeCours;
import com.ol.entities.HeureDeCoursDto;
import com.ol.entities.TypeMail;
import com.ol.exceptions.HeureDeCoursIndisponibleException;

@Service
public class CommandeService {

	@Autowired
	HeureDeCoursService heureDeCoursService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	MailService mailService;
	@Autowired
	PdfService pdfService;
	
	public void insererCommandeEnBase(CommandeDto commande) throws HeureDeCoursIndisponibleException, IOException, DocumentException {

		//pdfService.createFacture(commande);
		mailService.sendMail(commande, TypeMail.MAIL_POUR_MOI);
		mailService.sendMail(commande, TypeMail.FACTURE);
		mailService.sendMail(commande, TypeMail.INSCRIPTION);
		//pdfService.supprimerFacture(commande);
		
		for (HeureDeCoursDto heureDeCoursDto : commande.getRecapitulatifCommandeDto().getListeHeuresDeCoursDto()) {
			heureDeCoursService.reserverLeCours(
					heureDeCoursService.convertFromDtoToDBO(heureDeCoursDto), 
					clientService.convertFromDtoToDBO(commande.getClientDto()));
		}
	}
	
	public List<CommandeDto> addListeCommandeDto(List<CommandeDto> listeCommandeDto) throws HeureDeCoursIndisponibleException {
		List<CommandeDto> listeCommandeDtoRetour = new ArrayList<>();
		listeCommandeDto.stream().forEach(a ->{
			try {
				insererCommandeEnBase(a);
			} catch (HeureDeCoursIndisponibleException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return listeCommandeDtoRetour;
	}
}
