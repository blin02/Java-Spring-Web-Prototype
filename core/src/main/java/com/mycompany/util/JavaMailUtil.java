package com.mycompany.util;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util class that sends email using the JavaMail API.
 *
 */
public class JavaMailUtil {
	
	  public static enum EmailFormat {
		  	PLAIN_TEXT("text/plain"),
			HTML("text/html");
				
			private EmailFormat(String value) { 
				this.value = value;
			}
			
			private final String value;
			
			public String toString() { 
				return this.value; 
			}						
	  }
	
	private static final Logger log = LoggerFactory.getLogger(JavaMailUtil.class);
	private static Properties properties;
		
	private static Properties getProperties() {
		if (properties != null) {
			return properties;
		} else {			
			ResourceBundle settings = ResourceBundle.getBundle("SettingsBundle");
			String mailServer = settings.getString("mailserver");

			properties = new Properties();
			properties.put("mail.transport.protocol", "SMTP");
			properties.put("mail.smtp.host", mailServer);
			return properties;		
		}
	}

	public static void sendEmail(String fromEmailAddress, String toEmailAddress, 
			String subject, String bodyMsg, EmailFormat format) {
		
		sendEmail(fromEmailAddress, new String[]{toEmailAddress}, subject, bodyMsg, format);
		
	}
	
	public static void sendEmail(String fromEmailAddress, String[] toEmailAddressArray, 
			String subject, String bodyMsg, EmailFormat format) {

		try {			
			InternetAddress[] recipientAddressArray = new InternetAddress[toEmailAddressArray.length];			
			for (int i = 0; i < toEmailAddressArray.length; i++) {
				recipientAddressArray[i] = new InternetAddress(toEmailAddressArray[i]);				
			}
			
			MimeMessage message = new MimeMessage(Session.getInstance(getProperties()));
			message.setFrom(new InternetAddress(fromEmailAddress));
			message.setSubject(subject);			
			message.setRecipients(Message.RecipientType.TO, recipientAddressArray);

			message.setContent(bodyMsg, format.toString());
			
			Transport.send(message);
			log.info("Email Sent");

		} catch (MessagingException mex) {
			throw new RuntimeException(mex);

		} 
	}

}
