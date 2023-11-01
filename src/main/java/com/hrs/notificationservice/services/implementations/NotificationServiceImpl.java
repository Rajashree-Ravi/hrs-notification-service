package com.hrs.notificationservice.services.implementations;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.notificationservice.messaging.EmailSender;
import com.hrs.notificationservice.models.CustomerDto;
import com.hrs.notificationservice.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private EmailSender emailSender;

	@Override
	public void sendWelcomeEmail(CustomerDto customer) {
		log.info("Sending welcome email to '" + customer.getName() + "'");

		// Call the sendEmail method to send an email
		String recipientEmail = customer.getEmail();
		String subject = "Registration successful";
		String content = "<p>Hello,</p><p>This is a test email sent for registration.</p>";

		try {
			emailSender.sendEmail(recipientEmail, subject, content);
			log.info("Email sent successfully.");
		} catch (MessagingException | UnsupportedEncodingException e) {
			log.info("Failed to send email. Error: " + e.getMessage());
		}
	}

}
