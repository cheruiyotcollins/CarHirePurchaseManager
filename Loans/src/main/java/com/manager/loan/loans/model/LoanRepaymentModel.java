/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manager.loan.loans.model;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author collins
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRepaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loan_repayment_id")
    private Long loanRepaymentId;
    
    @Column(name="loan_id")
    private Long loanId;
    
    @Column(name="repayment_date")
    private LocalDateTime repaymentDate;
    
    @Column(name="repaymentAmount")
     private double repaymentAmount;

    

    
    
}
