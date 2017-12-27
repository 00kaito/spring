package com.demoproject.mydemo.Controllers;

import com.demoproject.mydemo.Models.Forms.FooterContactFormModel;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SendEmailService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static String sendEmail(Session session, String subject, String messageBody) {
        final String toEmail = "someemailadress@gmail.com";
        String result = "We've got a problem... Try again";
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress("no_reply@mydemoapp.com", "My Demo App"));
            msg.setReplyTo(InternetAddress.parse("no_reply@mydemoapp.com", false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(messageBody, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
            result = "Email was sent succesfully!";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String senditsimple(FooterContactFormModel form) {
        boolean validated = formValidation(form.getName(), form.getEmail(), form.getMessage());
        if (!validated)
            return "Form validation error";
        final String fromEmail = "smtp.mail.app@gmail.com";
        final String password = "pswdhere";
        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // fix error Could not convert socket to TLS
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        form.setsendingMessageFlag(true);
        return sendEmail(session, "Email from: " + form.getEmail(), form.getMessage());
    }

    public static boolean formValidation(String name, String emailFrom, String body) {
        int result = 0;
        boolean emailValiationResult = false;
        boolean bodyLengthValidationResult = false;
        boolean nameLengthValidationResult = false;

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailFrom);
        if (matcher.find()) {
            emailValiationResult = true;
            result++;
        }
        if (body.length() > 5) {
            bodyLengthValidationResult = true;
            result++;
        }
        if (name.length() > 3) {
            nameLengthValidationResult = true;
            result++;
        }
        if (result == 3)
            return true;
        return false;
    }
}