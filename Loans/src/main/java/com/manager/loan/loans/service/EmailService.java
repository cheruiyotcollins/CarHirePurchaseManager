package com.manager.loan.loans.service;

import com.manager.loan.loans.model.FailedEmails;
import com.manager.loan.loans.repository.FailedEmailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service("emailSender")
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

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            LOGGER.info("***Email Sent successfully ");
            return true;


        }catch(Exception e){
            if(className.equalsIgnoreCase("com.manager.loan.loans.service.LoanService")){
                LOGGER.info("*** Sending Email Failure, Saved to Database. It will be send when there is internet connection **** ");
                FailedEmails failedEmail= new FailedEmails();
                failedEmail.setEmailBody(body);
                failedEmail.setSubject(subject);
                failedEmail.setSendTo(to);
                failedEmail.setTime(dateTimeFormatter.format(LocalDateTime.now()));
                failedEmailsRepository.save(failedEmail);
                LOGGER.info("*** Sending Email Failure, Saved to Database. It will be send when there is internet connection **** ");

            }
            LOGGER.info("***  Email Resend Failure, it will be sent when connection is back **** ");

            return false;
        }

    }

    @Scheduled(fixedRate = 300000)
    public void CheckDefaultedLoans() {
        LOGGER.info("***Resending failed Emails");

        List<FailedEmails> allFailedMails = failedEmailsRepository.findAll();

        allFailedMails.stream().forEach(email->{

            if(sendMail(email.getSendTo(),email.getSubject(),email.getEmailBody())){
                LOGGER.info("***deleting sent emails from database**** ");
                failedEmailsRepository.deleteById(email.getId());


            }

        });

        LOGGER.info("***Failed Emails resend attempt completed" );

    }

}