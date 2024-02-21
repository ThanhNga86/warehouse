package com.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.assignment.entity.MailInfo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerServiceImp implements MailerService{
	List<MailInfo> list = new ArrayList<>();
	@Autowired
	JavaMailSender sender;
	@Override
	public void send(MailInfo mail) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);;
		// Gửi message đến SMTP server
		sender.send(message);
		
	}
	@Override
	public void queue(MailInfo mail) {
		// TODO Auto-generated method stub
		list.add(mail);
		
	}
	@Scheduled(fixedDelay = 5000)
		public void run() {
			while (!list.isEmpty()) {
				MailInfo mail = list.remove(0);
				try {
					this.send(mail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	
}
