package com.mycompany.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mycompany.service.EmailService;

/**
 * Class that sends email using Spring mail support.
 *
 */
@Component
@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
			
	@Override
	public void sendEmail(String fromEmailAddress, String toEmailAddress, 
			String subject, String bodyMsg, boolean isHtml) {
		
		sendEmail(fromEmailAddress, new String[]{toEmailAddress}, subject, bodyMsg, isHtml);
		
	}
	
	@Override
	public void sendEmail(String fromEmailAddress, String[] toEmailAddressArray, 
			String subject, String bodyMsg, boolean isHtml) {
		
		try {			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, false);

			messageHelper.setFrom(fromEmailAddress);
			messageHelper.setTo(toEmailAddressArray);
			messageHelper.setSubject(subject);
			messageHelper.setText(bodyMsg, isHtml);
			
			mailSender.send(message);

			log.info("Email Sent");
		} catch (MessagingException ex) {			
			throw new RuntimeException(ex); 
		}
	}
	
}


