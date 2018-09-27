package com.ymagis.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	// pour envoi email simple sans joindre des fichiers
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}
	//

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		try {
			// preparation les msgs MIME
			MimeMessage message = emailSender.createMimeMessage();
			// helper class pour la creation of MIME messages
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);

			helper.setText(text, true);
			// ajouter un attachement
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment(file.getFilename(), file);

			emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
