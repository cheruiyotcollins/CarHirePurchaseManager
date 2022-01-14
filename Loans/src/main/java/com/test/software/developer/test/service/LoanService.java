/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.software.developer.test.service;

import com.test.software.developer.test.dto.ExistById;
import com.test.software.developer.test.dto.SaveStatus;
import com.test.software.developer.test.dto.StringDto;
import com.test.software.developer.test.model.LoanModel;
import com.test.software.developer.test.repository.LoanRepository;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author collins
 */
@Service
@Component
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    private EmailService emailSender;

    //Constants
    private static final String VEHICLEURL = "http://localhost:9090/vehicle/model/exist/";
    private static final String CUSTOMERURL = "http://localhost:9191/customer/exist/";
    private static final String CUSTOMER_EMAIL = "http://localhost:9191/customer/email/";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    RestTemplate restTemplate = new RestTemplate();

    //Task scheduler format
    //@Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
    //12:00 pm everyday
    //@Scheduled(cron = "0 0 12 * * ?") 
    // 10:15 everyday in 2005
    //@Scheduled(cron = "0 15 10 * * ? 2005")
    //after an hour
    //@Scheduled(fixedRate = 3600000)
    @Scheduled(cron = "0 09 17 * * ?")
    public void CheckDefaultedLoans() {
        LocalDateTime todaysDate = LocalDateTime.now();
        LOGGER.info("***Start**Running scheduled task, checking defaulted loans" + todaysDate);

        List<LoanModel> allLoans = loanRepository.findAll();
      
        allLoans.stream().forEach(loanModel->{
            if (dateTimeFormatter.format(todaysDate).equals(dateTimeFormatter.format(loanModel.getLoanDueDate()))) {
                LOGGER.info("***Adding penalties to defaulted loans******");
                double outstandingAmount = loanModel.getOutstandingAmount();
                loanModel.setOutstandingAmount(outstandingAmount + (loanModel.getLoanAmount() * loanModel.getLoanInterest() / 100));
                loanModel.setLoanDueDate(todaysDate.plusDays(loanModel.getLoanDuration()));
                loanRepository.save(loanModel);
                sendEmailLoanDefault(loanModel);
        
        }});

        LOGGER.info("***Completed" + LocalDateTime.now());

    }

    public void LoanRepayment(double repaymentAmount, Long loanId) {
        LoanModel loanModel = loanRepository.getById(loanId);
        double outstandingAmount = loanModel.getOutstandingAmount();
        loanModel.setOutstandingAmount(outstandingAmount - repaymentAmount);
        loanRepository.save(loanModel);

    }

    //Email Templates Generator Functions
    public void sendEmailNewLoan(LoanModel loanModel) {
        StringDto nameNMail = new StringDto();
        nameNMail = restTemplate.getForObject(CUSTOMER_EMAIL + loanModel.getCustomerId(), StringDto.class);
        String sendTo = nameNMail.email;
        String subject = "Loan Confimation";
        String emailBody = "Dear " + nameNMail.name + "," + "\n" + "\n" + "Your Loan ID: " + loanModel.getLoanId() + " of amount KES " + loanModel.getLoanAmount() + " has successfully been processed. " + "A loan interest of " + loanModel.getLoanInterest() + "% has been applied. "
                + "Total repayable amount : KES " + loanModel.getOutstandingAmount() + " to be paid in " + loanModel.getLoanDuration() + " days. Please pay the total amount by " + dateTimeFormatter.format(loanModel.getLoanDueDate()) + " to continue enjoying our services." + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";
        emailSender.sendMail(sendTo, subject, emailBody);
    }

    public void sendEmailLoanEdited(LoanModel loanModel) {
        StringDto nameNMail = new StringDto();
        nameNMail = restTemplate.getForObject(CUSTOMER_EMAIL + loanModel.getCustomerId(), StringDto.class);
        String sendTo = nameNMail.email;
        String subject = "Loan Update Notificatin ";
        String emailBody = "Dear " + nameNMail.name + "," + "\n" + "\n" + "Your Loan ID: " + loanModel.getLoanId() + " of amount KES " + loanModel.getLoanAmount() + " has been updated. " + "A loan interest of " + loanModel.getLoanInterest() + "% has been applied."
                + "Total repayable amount: KES " + loanModel.getOutstandingAmount() + " repayable in " + loanModel.getLoanDuration() + " days. Please pay the total amount by " + dateTimeFormatter.format(loanModel.getLoanDueDate()) + ". to continue enjoying our services." + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";
        emailSender.sendMail(sendTo, subject, emailBody);
    }

    public void sendEmailLoanDefault(LoanModel loanModel) {
        StringDto nameNMail = new StringDto();
        nameNMail = restTemplate.getForObject(CUSTOMER_EMAIL + loanModel.getCustomerId(), StringDto.class);
        String sendTo = nameNMail.email;
        String subject = "Loan Default Notification";
        String emailBody = "Dear " + nameNMail.name + "," + "\n" + "\n" + "Your Loan ID: " + loanModel.getLoanId() + " of amount KES " + loanModel.getLoanAmount() + " is due today. A penalty "
                + "fee of KES " + loanModel.getLoanAmount() * loanModel.getLoanInterest() / 100 + " has been added to your loan. Repayment period has been extended by " + loanModel.getLoanDuration() + " days. Please pay the outstanding amount by " + dateTimeFormatter.format(LocalDateTime.now().plusDays(loanModel.getLoanDuration())) + " to avoid distruption of services." + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";
        emailSender.sendMail(sendTo, subject, emailBody);
    }
    //end of Email Template Functions

    public SaveStatus saveLoan(LoanModel loanModel) throws ParseException {
        Long vehicleId = loanModel.getVehicleMakeId();
        Long customerId = loanModel.getCustomerId();
        SaveStatus saveStatus = new SaveStatus();

        ExistById customerExist = new ExistById();
        ExistById vehicleExist = new ExistById();
        LOGGER.info("***customer id look up, calling customer microservice******");
        customerExist = restTemplate.getForObject(CUSTOMERURL + customerId, ExistById.class);
        vehicleExist = restTemplate.getForObject(VEHICLEURL + vehicleId, ExistById.class);

        if (customerExist.existById) {
            LOGGER.info("***vehicle id look up, calling vehicle microservice******");
            if (vehicleExist.existById) {
                double interest = 0;
                LOGGER.info("*******Setting up interest based on loan duration******");

                switch (loanModel.getLoanDuration()) {

                    case 3:
                        interest = 10;
                        break;
                    case 6:
                        interest = 20;
                        break;
                    case 12:
                        interest = 30;
                        break;

                }
                //Setting up loan model date and outstating amount     
                loanModel.setLoanDueDate(LocalDateTime.now().plusDays(loanModel.getLoanDuration()));
                loanModel.setDisbusedOnDate(LocalDateTime.now());
                loanModel.setLoanInterest(interest);
                double loanAmount = loanModel.getLoanAmount();
                loanModel.setOutstandingAmount(loanAmount + interest * loanAmount / 100);
                loanRepository.save(loanModel);
                saveStatus.status = "SUCCESS";
                saveStatus.description = "Processed succesfully";
                //sendEmailNewLoan(loanModel);

            } else {
                saveStatus.status = "FAIL";
                saveStatus.description = "Processing failed";
            }
        } else {
            saveStatus.status = "FAIL";
            saveStatus.description = "Processing failed";
        }
        return saveStatus;

    }

    public void updateLoan(LoanModel loanModel, Long id) {
        loanModel.setLoanId(id);
        Long vehicleId = loanModel.getVehicleMakeId();
        Long customerId = loanModel.getCustomerId();
        SaveStatus saveStatus = new SaveStatus();
        ExistById customerExist = new ExistById();
        ExistById vehicleExist = new ExistById();
        LOGGER.info("***customer id look up, calling customer microservice******");
        customerExist = restTemplate.getForObject(CUSTOMERURL + customerId, ExistById.class);
        vehicleExist = restTemplate.getForObject(VEHICLEURL + vehicleId, ExistById.class);

        if (customerExist.existById) {
            LOGGER.info("***vehicle id look up, calling vehicle microservice******");
            if (vehicleExist.existById) {
                double interest = 0;
                LOGGER.info("*******Setting up interest based on loan duration******");

                switch (loanModel.getLoanDuration()) {

                    case 3:
                        interest = 10;
                        break;
                    case 6:
                        interest = 20;
                        break;
                    case 12:
                        interest = 30;
                        break;

                }
                //Setting up loan model date and outstating amount     
                loanModel.setLoanDueDate(LocalDateTime.now().plusDays(loanModel.getLoanDuration()));
                loanModel.setDisbusedOnDate(LocalDateTime.now());
                loanModel.setLoanInterest(interest);
                double loanAmount = loanModel.getLoanAmount();
                loanModel.setOutstandingAmount(loanAmount + interest * loanAmount / 100);
                loanRepository.save(loanModel);
                saveStatus.status = "SUCCESS";
                saveStatus.description = "Processed succesfully";

                sendEmailLoanEdited(loanModel);

            } else {
                saveStatus.status = "FAIL";
                saveStatus.description = "Processing failed";
            }
        } else {
            saveStatus.status = "FAIL";
            saveStatus.description = "Processing failed";
        }

    }

    //Crud Operations
    public List<LoanModel> getAllLoans() {
        LOGGER.info("******getting all loans");
        return (List<LoanModel>) loanRepository.findAll();

    }

    public LoanModel getLoanById(long id) {
        return loanRepository.findById(id).get();
    }

    public void deleteLoan(long id) {
        loanRepository.deleteById(id);

    }
    
    //@Query
    
    public List<LoanModel> getLoansByVehicleMake(long vehicleMakeId) {
        return (List<LoanModel>) loanRepository.findLoanByVehicleMake(vehicleMakeId);
    }

    public List<LoanModel> getLoansDueOn(String loanDueDate) {
        loanDueDate += "%";
        return (List<LoanModel>) loanRepository.findLoanDueOn(loanDueDate);
    }

    public List<LoanModel> getLoansByDuration(int loanDuration) {
        return (List<LoanModel>) loanRepository.findLoanByDuration(loanDuration);
    }

    public List<LoanModel> getLoansMoreThan(double loanAmount) {
        return (List<LoanModel>) loanRepository.findLoanMoreThan(loanAmount);
    }

    public List<LoanModel> getLoansLessThan(double loanAmount) {
        return (List<LoanModel>) loanRepository.findLoanLessThan(loanAmount);
    }

    public List<LoanModel> getLoansDisbursedOn(String onDate) {
        onDate += "%";
        return (List<LoanModel>) loanRepository.findLoanDisbursedOn(onDate);
    }

    public List<LoanModel> getLoansDusbursedBetween(String fromD, String toD) {
        return (List<LoanModel>) loanRepository.findLoanDisbursedBetween(fromD,toD);
    }
    public List<LoanModel> getLoanByCustomerId(Long customerId) {
        return (List<LoanModel>) loanRepository.findLoanByCustomerId(customerId);
    }

}
