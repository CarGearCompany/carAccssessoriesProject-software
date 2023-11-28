package helpers;






import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;


public class EmailSenderService {

    private static final Logger logger = Logger.getLogger(EmailSenderService.class.getName());
    private EmailSenderService() {
    }

    public static void sendEmail(String senderEmail,String receiverEmail,String emailMessage,String subject,int flag) throws MessagingException{
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("cargearcompany@gmail.com","lyme qhpv jsvs arzy");
                }
            });
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail,false));
            message.setSubject(subject);
            message.setText(emailMessage);
            Transport.send(message);
            if(flag==0)
                logger.info("Order receipt is sent to your email.");
            else
                logger.info("Order receipt is sent to your installer's email.");

        }
        catch (MessagingException m){
           logger.warning("Notification failed to send");
        }







    }

}

