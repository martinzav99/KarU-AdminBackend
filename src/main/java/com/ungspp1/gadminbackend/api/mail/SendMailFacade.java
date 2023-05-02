package com.ungspp1.gadminbackend.api.mail;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.utils.NumberUtils;

import jakarta.mail.internet.MimeMessage;

@Component
public class SendMailFacade {

    @Autowired
    private JavaMailSender javaMailSender;

    public Object sendAutentcathionMail() {
        SimpleMailMessage message = createMessage();
        javaMailSender.send(message);
        return null;
    }

    private SimpleMailMessage createMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("aca va el mail de usuario");
        message.setTo("aca tenemos que crear un mail para la api");
        message.setSubject("No responder este correo electronico");
        message.setText(NumberUtils.RandomNumber());
        return message;
    }
    
}
