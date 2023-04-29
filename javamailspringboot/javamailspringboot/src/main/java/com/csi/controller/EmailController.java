package com.csi.controller;

import com.csi.model.EmailModel;
import com.csi.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/")
public class EmailController {
    @Autowired
    EmailServiceImpl emailServiceImpl;

    @PostMapping
    public ResponseEntity<String>sendEmail(@RequestBody EmailModel emailModel) throws MessagingException {
        emailServiceImpl.sendMail(emailModel);
        return ResponseEntity.ok("Email Send Successfully");
    }

    @GetMapping("say")
    public ResponseEntity<String>send(){
        return ResponseEntity.ok("dwgdyuwyudwy");
    }
}
