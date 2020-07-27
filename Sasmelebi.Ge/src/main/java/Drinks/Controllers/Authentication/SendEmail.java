package Drinks.Controllers.Authentication;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void send(String mail, String firstname, String lastname, String username, String password) {
        String to = mail;
        String from = "sasmelebi.ge@gmail.com";
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sasmelebi.ge@gmail.com", "oopdrinkproject123");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Sasmelebi.ge password reset instructions");
            message.setText("Hi " + firstname + " " + lastname +
                    "\nAs you have requested for your password, here is your password\n" + "Username: " + username +
                    "\nPassword: " + password);
            Transport.send(message);
            System.out.println("Message sent successfully");
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
