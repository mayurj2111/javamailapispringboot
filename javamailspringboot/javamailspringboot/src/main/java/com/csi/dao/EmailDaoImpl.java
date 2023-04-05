package com.csi.dao;

import com.csi.model.EmailModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
@Slf4j
public class EmailDaoImpl {
    @Autowired
    JavaMailSender mailSender;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail(){
    }

    public void sendMail(EmailModel emailModel) throws MessagingException {
        log.info("********To Email********"+emailModel.getToEmail());

        MimeMessage mimeMessage= mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("mayurj2111@gmail.com");
        mimeMessageHelper.setTo(emailModel.getToEmail());
        mimeMessageHelper.setCc(emailModel.getCcEmail());
        mimeMessageHelper.setText(emailModel.getEmailBody());
        mimeMessageHelper.setSubject(emailModel.getEmailSubject());

        FileSystemResource fileSystem =new FileSystemResource(new File(emailModel.getAttachement()));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);

        mailSender.send(mimeMessage);
        log.info("Mail Send Successfully");

    }
}
