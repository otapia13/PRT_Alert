package igno7um;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Alert {

    private String sender = "otapiadev@gmail.com";
    private String pass = "easypass123";
    private String receiver = "otapia13@yahoo.com";
    private int responseCode = 0;   // response code 1 success, -1 exception caught

    //sends email using gmail ssl
    public int sendEmail(String subject, String msg){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, pass);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            responseCode = 1;

        } catch (MessagingException e) {
            responseCode = -1;
            throw new RuntimeException(e);
        }

        return responseCode;
    }
}