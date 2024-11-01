package com.example.api_scotia.business;

import com.example.api_scotia.commons.utils.EmailFactory;
import com.example.api_scotia.service.EmailMessage;
import com.example.api_scotia.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailBusiness implements EmailService {

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(String type, String to, Map<String, Object> parameters) {
        EmailMessage message = EmailFactory.createLoanMessages(type, parameters);
        this.createEmail(to, "Notificación financiera", message.generateBody());
    }

    private void createEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        this.mailSender.send(message);
    }

}
