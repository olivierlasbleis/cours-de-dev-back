package com.ol.services;

import java.io.File;
import java.time.LocalDate;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ol.entities.Client;
import com.ol.entities.CommandeDto;
import com.ol.entities.HeureDeCoursDto;
import com.ol.entities.TypeMail;

@Service
public class MailService {
	
	@Value("${admin.email}")
	private String emailAdmin;

	
		
		public void sendMail(CommandeDto commandeDto, TypeMail typeMail) {
		final String username = "lasbleis.olivier29@gmail.com";
        final String password = "anEjnNU72d3CEH";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cours-de-dev.fr"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(commandeDto.getClientDto().getEmail())
            );
            if (typeMail == TypeMail.FACTURE) {
            	message.setSubject("Cours-de-dev-Facture");
            	
            	// Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText(getTextMailFacture(commandeDto));

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

             // Part two is attachment
                /**
                messageBodyPart = new MimeBodyPart();
                
                String filename = LocalDate.now().toString() + commandeDto.getClientDto().getNom() + commandeDto.getClientDto().getPrenom() + "-facture.pdf";
                DataSource source = new FileDataSource(new File("src/main/resources/" + filename));
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                 **/

                // Send the complete message parts
                message.setContent(multipart);
               
			}else if (typeMail == TypeMail.INSCRIPTION) {
				message.setSubject("Cours-de-dev-Inscription");

            	// Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText(getTextMailInscription(commandeDto));

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(multipart);
                
			}else if (typeMail == TypeMail.MAIL_POUR_MOI) {
            	message.setSubject("Cours-de-dev-nouvelle commande ! ");
            	
            	// Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText(getTextMailPourMoi(commandeDto));

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                /**
                messageBodyPart = new MimeBodyPart();
                
                String filename = LocalDate.now().toString() + commandeDto.getClientDto().getNom() + commandeDto.getClientDto().getPrenom() + "-facture.pdf";
                DataSource source = new FileDataSource(new File("src/main/resources/" + filename));
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                 **/
                // Send the complete message parts
                message.setContent(multipart);
			}
            
            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
		
	public static String getTextMailFacture(CommandeDto commandeDto) {
		String newLine = System.getProperty("line.separator");
		return "Bonjour" + commandeDto.getClientDto().getPrenom()
				+ newLine + "Merci pour votre commande"
				+ newLine 
				+ newLine + "Vous trouverez ci-joint la facture correspondant à votre paiement"
				+ newLine 
				+ newLine + "Au plaisir de vous voir en cours !"
				+ newLine 
				+ newLine + "Cordialement"
				+ newLine 
				+ newLine 
				+ newLine + "Olivier Lasbleis"
				+ newLine + "06 99 89 42 22";
	}
	public static  String getTextMailPourMoi(CommandeDto commandeDto) {
		String newLine = System.getProperty("line.separator");
		String listeHeuresDeCours = 
				commandeDto.getRecapitulatifCommandeDto().getListeHeuresDeCoursDto()
				.stream()
				.map(hdc -> newLine + "_le " + hdc.getDateJour() 
							+ "à" + hdc.getDateHeure()).collect(Collectors.joining());
		return "Bien joué !"
				+ newLine 
				+ newLine + "Nouveaux cours : " + listeHeuresDeCours
				+ newLine + "avec " + commandeDto.getClientDto().getNom() + commandeDto.getClientDto().getPrenom();
				
	}
	public static  String getTextMailInscription(CommandeDto commandeDto) {
		String newLine = System.getProperty("line.separator");
		String listeHeuresDeCours = 
				commandeDto.getRecapitulatifCommandeDto().getListeHeuresDeCoursDto()
				.stream()
				.map(hdc -> newLine + "_le " + hdc.getDateJour() 
							+ "à" + hdc.getDateHeure()).collect(Collectors.joining());
		return "Bonjour" + commandeDto.getClientDto().getPrenom()
				+ newLine 
				+ newLine + "Ton inscription a bien été prise en compte, soit la bienvenue dans le programme de formation"
				+ newLine + "Afin de commencer sur de bonnes bases je t'invite à lire attentivement ce qui suit afin de découvrir comment vont se passer nos cours"
				+ newLine 
				+ newLine + "Nous avons rdv le(s) : " + listeHeuresDeCours
				+ newLine 
				+ newLine + "Pour chacun des cours que nous aurons je t'enverrai par mail, quelques minutes avant, un lien pour acceder à la visioconférence."
				+ newLine 
				+ newLine + "Comment préparer notre premier cours ?"
				+ newLine + "1_ En répondant au questionnaire suivant : "
				+ newLine + "	Il me permettra d'avoir quelques informations sur ton niveau actuel en programmation"
				+ newLine + "2_ En installant le logiciel Visual Studio Code "
				+ newLine + "	Il me permettra d'avoir quelques informations sur ton niveau actuel en programmation"
				+ newLine 
				+ newLine + "A bientôt !"
				+ newLine 
				+ newLine + "Olivier Lasbleis"
				+ newLine + "06 99 89 42 22";
	}
}
