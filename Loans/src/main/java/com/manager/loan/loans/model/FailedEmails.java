package com.manager.loan.loans.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "failed_mails")
public class FailedEmails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sendTo;
    private String subject;
    private String emailBody;
    private String time;

}