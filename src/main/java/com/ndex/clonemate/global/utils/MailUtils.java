package com.ndex.clonemate.global.utils;

import com.ndex.clonemate.global.dto.MailRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailUtils {
    @Value("${mail.smtp.port}")
    private String PORT;

    @Value("${mail.smtp.auth}")
    private String AUTH;

    @Value("${mail.smtp.starttls.enable}")
    private String STARTTLS_ENABLE;

    @Value("${mail.email}")
    private String EMAIL;

    @Value("${mail.password}")
    private String PASSWORD;

    @Value("${mail.authenticator.id}")
    private String ID;

    @Value("${mail.authenticator.password}")
    private String SECOND_PASSWORD;

    @Value("${mail.content.type}")
    private String CONTENT_TYPE;

    @Value("${mail.content.charset}")
    private String CHARSET;

    private static Properties serverProperties;
    private static Session mailSession;
    private static MimeMessage mail;

    public void send(MailRequestDto request) {
        try {
            serverProperties = System.getProperties();
            serverProperties.put("mail.smtp.port", PORT);
            serverProperties.put("mail.smtp.auth", AUTH);
            serverProperties.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);

            Authenticator auth = new SMTPAuthenticator();
            mailSession = Session.getDefaultInstance(serverProperties, auth);

            mail = new MimeMessage(mailSession);
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getEmail()));
            mail.setSubject(request.getSubject());
            mail.setText(request.getContents(), CHARSET, CONTENT_TYPE);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends Authenticator {
        String id = ID;
        String pwd = SECOND_PASSWORD;

        SMTPAuthenticator() {
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(id, pwd);
        }
    }
}
