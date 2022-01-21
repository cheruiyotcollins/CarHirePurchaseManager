/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.service;

import com.loan.users.model.FailedEmails;
import com.loan.users.repository.FailedEmailsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author hp pc
 */
@Service("emailSender")
@Component
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    FailedEmailsRepository failedEmailsRepository;

    public boolean sendMail(String to, String subject, String body) {
        StackWalker walker = StackWalker
                .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        Class<?> callerClass = walker.getCallerClass();
       String className= callerClass.getName();
        LOGGER.info("***sending Email**** ");

        boolean sent=false;
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            LOGGER.info("***Email Sent successfully ");
            return sent=true;


        }catch(Exception e){
          if(className.equalsIgnoreCase("com.loan.users.service.CustomerService")){
            FailedEmails failedEmail= new FailedEmails();
            failedEmail.setEmailBody(body);
            failedEmail.setSubject(subject);
            failedEmail.setSendTo(to);
            failedEmail.setTime(dateTimeFormatter.format(LocalDateTime.now()));
            failedEmailsRepository.save(failedEmail);
              LOGGER.info("*** Sending Email Failure, Saved to Database. It will be send when there is internet connection **** ");

          }
            LOGGER.info("***  Email Resend Failure, it will be sent when connection is back **** ");

            return sent;
        }

    }

    @Scheduled(fixedRate = 60000)
    public void CheckDefaultedLoans() {
        LOGGER.info("***stating failed mail resend ");

        List<FailedEmails> allFailedMails = failedEmailsRepository.findAll();

        allFailedMails.stream().forEach(email->{

                if(sendMail(email.getSendTo(),email.getSubject(),email.getEmailBody())){
                    LOGGER.info("***here removals**** ");
                   failedEmailsRepository.deleteById(email.getId());


                }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

        LOGGER.info("***Completed failed mail resend" );

    }

}
