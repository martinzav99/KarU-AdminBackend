package com.ungspp1.gadminbackend.api.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.utils.NumberUtils;


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
        message.setFrom("losmagiostest@gmail.com");
        message.setTo("conte.ignacio03@gmail.com" , "gastoncarp2012@gmail.com");
        message.setSubject("esto es una prueba por favor no me mates");
        message.setText(NumberUtils.RandomNumber());
        return message;
    }
    
}
