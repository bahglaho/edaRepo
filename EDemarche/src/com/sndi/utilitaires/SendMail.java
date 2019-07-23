package com.sndi.utilitaires;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

public class SendMail {
	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for TLS/STARTTLS: 587
	   Author Bah Hervé
	 */
	
	private static final String fromEmail = GRFProperties.EMAIL_FROM; //requires valid gmail id
	private static final String password = GRFProperties.EMAIL_PASSWORD; // correct password for gmail id
	

	private static Properties initProp() {
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", GRFProperties.EMAIL_HOST); //SMTP Host
		props.put("mail.smtp.port", GRFProperties.EMAIL_PORT); //TLS Port
		props.put("mail.smtp.auth", GRFProperties.EMAIL_AUTH); //enable authentication
		props.put("mail.smtp.starttls.enable", GRFProperties.EMAIL_TSL_ENABLE); //enable STARTTLS
		return props;
	}
	@Async
	public static void envoiMail(String codeExpImp, String objet, String pointFocal, String statut, String delai, List<String> destinataireEmail, List<String> initiateurEmail) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, destinataireEmail, initiateurEmail,"SIGMission- Dossier N° "+codeExpImp+" : "+objet, "Une Mission du "+pointFocal +" Statut du Dossier: "+statut+" est en attente. Merci de bien vouloir traiter ce dossier dans un delai de "+delai);
		
	}
	
	@Async
	public static void envoiMailDiffere(String codeExpImp, String objet, String pointFocal, String statut, String delai, List<String> destinataireEmail, List<String> initiateurEmail) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, destinataireEmail, initiateurEmail,"SIGMission- Dossier N° "+codeExpImp+" : "+objet, "Une Mission du "+pointFocal +" Statut du Dossier: "+statut+" est en attente. Merci de bien vouloir traiter ce différé");
		
	}
	
	@Async
	public static void envoiMailRelance(String objet, String msg, String statut, String delai ) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		//EmailUtil.sendEmail(session, toEmail, "","SIGMission-"+objet, "TLSEmail Testing SIGMission Body");
		
	}
	
	@Async
	public static void envoiMailEnveloppe(String exercice, String ministere, List<String> destinataireEmail, List<String> initiateurEmail) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, destinataireEmail, initiateurEmail,"SIGMission- Transmission de l'Enveloppe Budgétaire","Bonjour, L'Enveloppe Budgétaire du "+ministere+" est disponible. Merci ");
		
		
	}
	
	@Async
	public static void envoiMailCloture(String codeExpImp, String objet, String pointFocal, String statut, List<String> destinataireEmail, List<String> initiateurEmail) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, destinataireEmail, initiateurEmail,"SIGMission- Dossier N° "+codeExpImp+" : "+objet, "Une Mission du "+pointFocal +". Statut du Dossier: "+statut+" (Paiement des 20% effectué par le régisseur) Prière cloturer le dossier.");
		
	}
	
	@Async
	public static void envoiMailListeNoire(String codeExpImp, List<String> destinataireEmail, List<String> initiateurEmail, List<String> participant) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, destinataireEmail, initiateurEmail,"SIGMission- Dossier N° "+codeExpImp+" : Liste Noire", "Le délai de deux (2) semaines pour la transmission des rapports de mission pour la liquidation des 20% n'ayant pas été respecté, tous les participants suivant ont été bloqués:"+participant);
		
	}
	
	@Async
	public static void envoiMailListeNoireCredit(String codeExpImp, List<String> destinataireEmail, List<String> initiateurEmail, List<String> participant) {
		Properties props = initProp();
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		EmailUtil.sendEmail(session, destinataireEmail, initiateurEmail,"SIGMission- Dossier N° "+codeExpImp+" : Liste Noire", "Les participants suivants doivent reverser au tresor un trop perçu à l'issu de la liquidation des 20%:"+participant);
		
	}

}	
