/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.software.developer.test.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author collins
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanModel implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "vehicle_make_id")
    private Long vehicleMakeId;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "loan_interest")
    private double loanInterest;

    @Column(name = "outstanding_amount")
    private double outstandingAmount;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "disbursed_on_date")
    private LocalDateTime disbusedOnDate;
    
    
    @Column(name = "loan_due_date")
    private LocalDateTime loanDueDate;

    @Column(name = "loan_duration")
    private int loanDuration;

}
