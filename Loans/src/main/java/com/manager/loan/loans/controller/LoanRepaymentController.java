/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manager.loan.loans.controller;

import com.manager.loan.loans.model.LoanRepaymentModel;
import com.manager.loan.loans.service.LoanRepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author collins
 */
@RestController
@RequestMapping(value = "/repayment", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanRepaymentController {

    @Autowired
    LoanRepaymentService loanRepaymentService;
     
    @PostMapping("/add")
    public LoanRepaymentModel loanRepayment(@RequestBody LoanRepaymentModel loanRepaymentModel) throws Exception {
        loanRepaymentService.saveOrUpdate(loanRepaymentModel);
        return loanRepaymentModel;
    }

}
