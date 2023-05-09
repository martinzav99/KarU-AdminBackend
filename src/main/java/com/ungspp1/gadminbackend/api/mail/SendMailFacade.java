package com.ungspp1.gadminbackend.api.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class SendMailFacade {

    @Autowired
    private JavaMailSender javaMailSender;

    public Object sendAutentcathionMail(String mail, String code) {
        SimpleMailMessage message = createMessage(mail, code);
        javaMailSender.send(message);
        return null;
    }

    private SimpleMailMessage createMessage(String mail, String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("losmagiostest@gmail.com");
        message.setTo(mail);
        message.setSubject("Autenticación en dos pasos");
        message.setText("Tu codigo de autenticación es: "+code);
        return message;
    }
    
}
