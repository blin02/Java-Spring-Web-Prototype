package com.mycompany.service;


/**
 * Util class that sends email using Spring mail support.
 *
 */
public interface EmailService {
				
	public void sendEmail(String fromEmailAddress, String toEmailAddress, String subject, String bodyMsg, boolean isHtml);
	
	public void sendEmail(String fromEmailAddress, String[] toEmailAddressArray, String subject, String bodyMsg, boolean isHtml);	

}


