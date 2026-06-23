package com.voting.email.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class VoterCastVoteMailService {

	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	  private String fromEmail; 

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

	public void sendVoteConfirmationMail(String toEmail, String voterName) {

		LocalDateTime now = LocalDateTime.now();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setFrom(fromEmail);
		message.setSubject("Vote Cast Successfully | Votezy");

		message.setText("""
				Hello %s,

				✅ Your vote has been successfully cast!

				Thank you for participating in the election.
				Your vote was recorded on %s.

				🔐 Platform Assurance:
				• Your vote is secure and confidential
				• The voting process is transparent and tamper-proof
				• Votezy ensures every vote is counted fairly

				🚀 Next Steps:
				✔ You can monitor election results on your dashboard
				✔ Ensure your contact information is up-to-date for notifications

				⚠ Important Notice:
				Votezy maintains a neutral platform and does not share your vote with any party or candidate.

				Warm regards,
				Team Votezy
				Empowering Digital Democracy
				""".formatted(voterName, now.format(FORMATTER)));

		sender.send(message);
	}
}
