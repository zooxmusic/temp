package com.aeg.ims.transfer.controller;

import com.aeg.ims.transfer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/mail")
    @ResponseStatus(HttpStatus.CREATED)
    public String send() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("brian@109forest.com");
        mailMessage.setReplyTo("transfer@ims.com");
        mailMessage.setSubject("Transfer Status");
        mailMessage.setText("Lorem ipsum dolor sit amet [...]");
        mailService.send(mailMessage);

        return "success";
    }
}
