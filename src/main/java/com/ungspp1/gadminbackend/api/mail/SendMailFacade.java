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

    public Object sendTokenMail(String mail, String code) {
        SimpleMailMessage message = recoverPassMessage(mail, code);
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

    private SimpleMailMessage recoverPassMessage(String mail,String code){
        String url = "http://localhost:8080/resetPassword?token=" + code;
        String mensaje = String.format("Hola Usuario, para reestablecer tu contraseña haz clic en el siguiente enlace:%n%s%n", url);;
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("losmagiostest@gmail.com"); 
        message.setTo("martinkapo99@gmail.com");
        message.setSubject("Recuperar password");
        message.setText(mensaje);
        return message;
    }
    
}
