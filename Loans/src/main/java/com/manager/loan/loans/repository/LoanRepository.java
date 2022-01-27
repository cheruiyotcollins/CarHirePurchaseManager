/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manager.loan.loans.repository;

import com.manager.loan.loans.model.LoanModel;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author collins
 */
public interface LoanRepository extends JpaRepository<LoanModel,Long> {
    
        @Query(
            value = "SELECT * FROM loan_model l WHERE l.vehicle_make_id = :vehicleMake",
            nativeQuery = true)
    Collection<LoanModel> findLoanByVehicleMake(@Param("vehicleMake") Long vehicleMake);

    @Query(
            value = "SELECT * FROM loan_model l WHERE l.loan_due_date LIKE :loanDueDate",
            nativeQuery = true)
    Collection<LoanModel> findLoanDueOn(@Param("loanDueDate") String loanDueDate);

    @Query(
            value = "SELECT * FROM loan_model l WHERE l.loan_duration = :LoanDuration",
            nativeQuery = true)
    Collection<LoanModel> findLoanByDuration(@Param("LoanDuration") int duration);

    @Query(
            value = "SELECT * FROM loan_model l WHERE l.loan_amount>= :loanAmount",
            nativeQuery = true)
    Collection<LoanModel> findLoanMoreThan(@Param("loanAmount") double loanAmount);
    
     @Query(
            value = "SELECT * FROM loan_model l WHERE l.loan_amount<= :loanAmount",
            nativeQuery = true)
    Collection<LoanModel> findLoanLessThan(@Param("loanAmount") double loanAmount);

    @Query(
            value = "SELECT * FROM loan_model l WHERE l.disbursed_on_date LIKE :disbursedOn",
            nativeQuery = true)
    Collection<LoanModel> findLoanDisbursedOn(@Param("disbursedOn") String disbursedOn);

    @Query(
            value = "SELECT * FROM loan_model l WHERE l.disbursed_on_date8                                                                                                                                                                                               VBETWEEN :fromD AND :toD",
            nativeQuery = true)
    Collection<LoanModel> findLoanDisbursedBetween(@Param("fromD") String fromDate, @Param("toD") String toDate);
    
       @Query(
            value = "SELECT * FROM loan_model l WHERE l.customer_id =:custormerId",
            nativeQuery = true)
    Collection<LoanModel> findLoanByCustomerId(@Param("custormerId") Long customerId);
    
}
