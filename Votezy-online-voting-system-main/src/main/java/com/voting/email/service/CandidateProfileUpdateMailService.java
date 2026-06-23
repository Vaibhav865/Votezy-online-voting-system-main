package com.voting.email.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CandidateProfileUpdateMailService {

    @Autowired
    private JavaMailSender sender;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    public void sendProfileUpdateMail(String toEmail, String candidateName, String oldParty, String newParty) {

        LocalDateTime now = LocalDateTime.now();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Candidate Profile Updated | Votezy");

        message.setText("""
                Hello %s,

                🔄 Candidate Profile Updated!

                Your profile on Votezy was updated successfully on %s.

                🗳 Updated Candidate Details:
                • Candidate Name: %s
                • Previous Party: %s
                • Current Party: %s
                • Status: Verified & Active

                🔐 Platform Assurance:
                • Secure and transparent voting system
                • Equal opportunity for all candidates
                • Tamper-proof digital voting

                🚀 Next Steps:
                ✔ Log in to your candidate dashboard
                ✔ Review your updated profile
                ✔ Track election status and results

                ⚠ Important Notice:
                Votezy remains a neutral platform and does not promote or endorse any political party.

                If you did not make this change, please contact our support team immediately.

                Warm regards,
                Team Votezy
                Empowering Digital Democracy
                """.formatted(candidateName, now.format(FORMATTER), candidateName, oldParty, newParty));

        sender.send(message);
    }
}

