package com.sndi.utilitaires;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	@Async
	public static void sendEmail(Session session, List<String> desEmail,List<String> iniEmail, String subject, String body){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("herve.bah@sndi.ci", "Alerte SIGMission"));
	      //List des CC
	      for(String cMail:iniEmail) {
	    	  msg.addRecipient(RecipientType.CC, new InternetAddress(cMail, ""));  
	      }
	      
	      msg.addRecipient(RecipientType.CC, new InternetAddress("herve.bah@sndi.ci", ""));
	      //msg.addRecipient(RecipientType.CC, new InternetAddress("c.nguessan@sndi.ci", ""));

	      msg.setReplyTo(InternetAddress.parse("herve.bah@sndi.ci", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      
	      //List des To
	      for(String toMail:desEmail) {
	    	  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false));
	      }
	      System.out.println("Email transmis");
    	  Transport.send(msg);  

	      System.out.println("EMail transmis avec succes!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
}