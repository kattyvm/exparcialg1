package exparcialg1.demo.utils;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Component
public class CustomMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() throws javax.mail.MessagingException, IOException{

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("a20160555@pucp.edu.pe");

        msg.setSubject("Recuperar contraseña");
        msg.setText("Usted solicitó la recuperación de la contraseña en la Tiendita de Don Pepe." +
                "\n La nueva contraseña es: " );

        javaMailSender.send(msg);

    }

    public void sendTestEmailWithAtachment() throws javax.mail.MessagingException, IOException{
        MimeMessage msg = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("a20160555@pucp.edu.pe");
        helper.setSubject("Testing from Spring Boot");
        // default = text/plain
        //helper.setText("Check attachment for image!");
        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);
        FileSystemResource file = new FileSystemResource(new File("/Users/katty/Desktop/aaa/test.txt"));
        helper.addAttachment("test.txt", file);
        javaMailSender.send(msg);
    }

    public void sendEmailWithAtachment(String to, String subject, String title, String message, FileSystemResource file) throws javax.mail.MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message);
        helper.setText("<h1>"+title+"</h1>", true);
        helper.addAttachment(file.getFilename(), file);
        javaMailSender.send(msg);
    }

    public void sendEmail(String to, String subject, String title, String message) throws javax.mail.MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message);
        helper.setText("<h1>"+title+"</h1>", true);
        javaMailSender.send(msg);
        helper.setText("<h1>Check attachment for image!</h1>", true);
        javaMailSender.send(msg);

    }

}
