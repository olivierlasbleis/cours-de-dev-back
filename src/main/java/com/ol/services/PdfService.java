package com.ol.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.ol.entities.CommandeDto;

@Service
public class PdfService {
	
	
	public void supprimerFacture(CommandeDto commande) throws IOException, DocumentException {
		String filename = LocalDate.now().toString() + commande.getClientDto().getNom() + commande.getClientDto().getPrenom() + "-facture.pdf";
	    String dest = "src/main/resources/" + filename;
	   
		File file = new File(dest); 
        
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
    
    }

	public void createFacture(CommandeDto commande) throws IOException, DocumentException {
		String src = "src/main/resources/modeleFacture.pdf";
		String filename = LocalDate.now().toString() + commande.getClientDto().getNom() + commande.getClientDto().getPrenom() + "-facture.pdf";
	    String dest = "src/main/resources/" + filename;
	    
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            PRStream stream = (PRStream)object;
            byte[] data = PdfReader.getStreamBytes(stream);
            String stringContent = new String(data);
            stringContent = stringContent.replace("<Nom-entreprise>", "COURS-DE-DEV");
			stringContent = stringContent.replace("<Numero-siren>", "12501469458745");
        	stringContent = stringContent.replace("<Adresse-email>", "lasbleis.olivier@yahoo.fr");
        	stringContent = stringContent.replace("<Date-facture>", LocalDate.now().toString());
				stringContent = stringContent.replace("<Numero-client>", "125014");
	        	stringContent = stringContent.replace("<Nom-client>", commande.getClientDto().getNom() + " " + commande.getClientDto().getPrenom());
	        	stringContent = stringContent.replace("<adresse-client>", "12 rue du tertre");
				stringContent = stringContent.replace("<CP-ville-client>", "29000 Quimper");
				stringContent = stringContent.replace("<Quantite>", 
						String.valueOf(commande.getRecapitulatifCommandeDto().getListeHeuresDeCoursDto().size()));
				stringContent = stringContent.replace("<Designation>", "Cours particulier(s) de développement informatique");
				stringContent = stringContent.replace("<Prix-HT>", 
						String.valueOf(
								commande.getRecapitulatifCommandeDto().getListeHeuresDeCoursDto()
								.stream().mapToInt(hdc -> hdc.getPrix()).sum()));
				stringContent = stringContent.replace("<Id-Stripe>", commande.getIdPaiementStripe());
				stringContent = stringContent.replace("<Reference-facture>", "36589");
			
            stream.setData(stringContent.getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
		
	}

}
