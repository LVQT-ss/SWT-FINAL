package tech.rent.be.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tech.rent.be.dto.EmailDetail;
import tech.rent.be.utils.TokenHandler;


@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    TokenHandler tokenHandler;
    Context context = new Context();

    public void sendEmail(EmailDetail emailDetail){


        try{

            context.setVariable("name", emailDetail.getUser().getFullname() );
            context.setVariable("link", "http://4rent.tech:8081/active?token="+tokenHandler.generateToken(emailDetail.getUser()));

            String text = templateEngine.process("emailTemplate", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(emailDetail.getUser().getEmail());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject("Thư đe dọa");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }

    public void testEmail(String email){

        try{
            Context context = new Context();
            context.setVariable("name", "phong");
            context.setVariable("link", "Gia Bảo");

            String text = templateEngine.process("emailtemplate", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo("phuocha275@gmail.com");
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject("Thư đe dọa");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }
}
