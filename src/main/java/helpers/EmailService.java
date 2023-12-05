package helpers;









import java.nio.file.Files;
import java.nio.file.Paths;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.Random;


public class EmailService {
    private static String body;


    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    private EmailService() {

    }
    public static String sendEmailVerification(String senderEmail,String receiverEmail) {
        String code;
        String path = "<img src=\"src/main/resources/imgs/logo.png\">";
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

            code = generateRandomString();
            body = "To user : " + receiverEmail + "<br>"
                    + "this is your verification code: " +"<br><br><br><br><br>"+ code+ "<br><br><br><br><br><br>";


            message.setSubject("Email Verification");

            setBody(code,"src/main/resources/email-verification.html","CarGear Email Verification",path,1);




            //set the content (Multi part body consists of multi bodies)
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(body, "text/html");

//            MimeBodyPart imageBodyPart = new MimeBodyPart();
//            String imagePath = "imgs/logo.png";
//            DataSource source = new FileDataSource(imagePath);
//            imageBodyPart.setDataHandler(new DataHandler(source));
//            imageBodyPart.setDisposition(MimeBodyPart.INLINE);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textBodyPart);
           // multipart.addBodyPart(imageBodyPart);
            message.setContent(multipart);


            Transport.send(message);
            logger.info("Verification code is sent to your email.");

            return code;




        }
        catch (MessagingException m){
            logger.warning("Notification failed to send");
        } catch (IOException e) {
            logger.warning("File failed to reach");

      }
        return null;

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
            boolean bool = flag == 1;
            setBody(emailMessage,"src/main/resources/email-notification.html",bool?"Installation Request Notification":"Purchase Order Notification","",0);



            //set the content (Multi part body consists of multi bodies)
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);








            Transport.send(message);
            if(flag==0)
                logger.info("Order receipt is sent to your email.");
            else
                logger.info("Order receipt is sent to your installer's email.");

        }
        catch (MessagingException m){
           logger.warning("Notification failed to send");
        } catch (IOException e) {
            logger.warning("File failed to reach");
        }


    }

    private static String generateRandomString() {
        StringBuilder randomStr = new StringBuilder();
        for(int i = 0; i < 5; i++)
            randomStr.append(generateRandomDigit());
        return randomStr.toString();
    }

    private static int generateRandomDigit() {

        return new Random().nextInt(10);
    }

    private static void setBody(String bodyReplacement,String path,String subjReplacement,String imgReplacement ,int flag) throws IOException {
        // read the html
        body = Files.readString(Paths.get(path));
        // replace the holder
        body = body.replace("{{dynamic_subject_placeholder}}", subjReplacement);
        body = body.replace("{{dynamic_text_placeholder}}", bodyReplacement);
        if(flag==1)
             body = body.replace("{{dynamic_image_placeholder}}",imgReplacement);
    }




}

