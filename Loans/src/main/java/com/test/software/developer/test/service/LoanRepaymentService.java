package com.test.software.developer.test.service;

import com.test.software.developer.test.model.LoanRepaymentModel;
import com.test.software.developer.test.repository.LoanRepaymentRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoanRepaymentService {

    @Autowired
    LoanRepaymentRepository  loanRepaymentRepository;
    
    @Autowired
    LoanService  loanService;
    
    
    
//Crud operations
    public List<LoanRepaymentModel> getAllLoanRepayments(){
        return (List<LoanRepaymentModel>) loanRepaymentRepository.findAll();
        
    }

    public LoanRepaymentModel getRepaymentById(long id){
        return (LoanRepaymentModel) loanRepaymentRepository.getById(id);
    
    }

    public void saveOrUpdate(LoanRepaymentModel loanRepaymentModel){
       Long loanId= loanRepaymentModel.getLoanId();
       loanRepaymentModel.setRepaymentDate(LocalDateTime.now());
       double repaymentAmount= loanRepaymentModel.getRepaymentAmount();
      loanService.LoanRepayment(repaymentAmount, loanId );
       
     loanRepaymentRepository.save(loanRepaymentModel);
    }

    public void deletePayment(long id){
        loanRepaymentRepository.deleteById(id);
    
    }

}
