/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manager.loan.loans.repository;

import com.manager.loan.loans.model.LoanRepaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author collins
 */
public interface LoanRepaymentRepository extends JpaRepository<LoanRepaymentModel,Long>{
    
}
