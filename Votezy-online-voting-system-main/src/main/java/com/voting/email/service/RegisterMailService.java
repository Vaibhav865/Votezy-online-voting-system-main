package com.voting.email.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RegisterMailService {
	
	@Autowired
	private JavaMailSender sender;
	
	private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
	
	 public void sendRegistrationMail(String toEmail, String userName,String party) {

		  LocalDateTime now = LocalDateTime.now();
		  
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("Registration Successful");
	        message.setText("""
	                Hello %s,

	                🎉 Registration Successful!

	                Thank you for registering as a candidate on Votezy.
	                Your registration was completed successfully on %s.

	                🗳 Candidate Details:
	                • Registered Party: %s
	                • Status: Verified & Active

	                🔐 Platform Assurance:
	                • Secure and transparent voting process
	                • Equal opportunity for all candidates
	                • Tamper-proof digital voting

	                🚀 Next Steps:
	                ✔ Log in to your candidate dashboard
	                ✔ Review your profile and party details
	                ✔ Track election status and results

	                ⚠ Important Notice:
	                Votezy is a neutral platform and does not promote or endorse any political party.

	                If you did not request this registration, please contact our support team immediately.

	                Warm regards,
	                Team Votezy
	                Empowering Digital Democracy
	                """.formatted(userName, now.format(FORMATTER), party));

	        sender.send(message);
	    }
}
