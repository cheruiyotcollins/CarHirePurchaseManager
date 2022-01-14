/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.service;

import com.loan.users.model.Customer;
import com.loan.users.repository.CustomerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author collins
 */
@Service
@Component
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailService emailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    //Crud Operations
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll(Sort.by(Sort.Direction.ASC, "customerName"));

    }

    public List<Customer> getAllActiveCustomers() {
        return (List<Customer>) customerRepository.findAllActiveCustomers();
    }

    public List<Customer> getAllInactiveCustomers() {
        return (List<Customer>) customerRepository.findAllInactiveCustomers();
    }

    public List<Customer> getAllMaleCustomers() {
        return (List<Customer>) customerRepository.findAllMaleCustomers();
    }

    public List<Customer> getAllFemaleCustomers() {
        return (List<Customer>) customerRepository.findAllFemaleCustomers();
    }

    public List<Customer> getAllCustomersRegisteredBetween(String fromDate, String toDate) {
        return (List<Customer>) customerRepository.findCustomersRegisteredBetween(fromDate, toDate);
    }

    public List<Customer> getAllCustomersRegisteredOn(String onDate) {
        onDate += "%";
        return (List<Customer>) customerRepository.findCustomersRegisteredOn(onDate);
    }
    @Async("asyncExecutor")
    public List<Customer> getCustomersByName(String names) {
        names += "%";
        return (List<Customer>) customerRepository.findCustomerByName(names);
    }
    @Async("asyncExecutor")
    public Customer getCustomerById(long id) {
        LOGGER.info("*******customer id look up**");
        return customerRepository.findById(id).get();
    }
        public boolean customerExistById(long id) {
        LOGGER.info("*******checking if customer exist **");
        return customerRepository.existsById(id);
    }
    @Async("asyncExecutor")
    public void saveCustomer(Customer customer){
        LOGGER.info("*******Incomming request to save customer**");
        customer.setRegisteredOn(LocalDateTime.now());
        customerRepository.save(customer);
        sendEmailNewCustomer(customer);
    }
    @Async("asyncExecutor")
    public void updateCustomer(Customer customer, Long id) {
        customer.setRegisteredOn(LocalDateTime.now());
        customerRepository.save(customer);
        sendEmailCustomerUpdate(customer);
    }
    @Async("asyncExecutor")
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);

    }

    public void sendEmailNewCustomer(Customer customer) {

        String sendTo = customer.getEmail();
        String subject = "Registration Confirmation";
        String emailBody = "Dear " + customer.getCustomerName() + "," + "\n" + "\n" + "Your have been successfully onboarded to Collins Motor Ltd platform. You can now apply for a car loan and pay in a period of upto 3 months." + "\n" + "**Thank you for choosing us**" + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";
        emailSender.sendMail(sendTo, subject, emailBody);
    }

    public void sendEmailCustomerUpdate(Customer customer) {

        String sendTo = customer.getEmail();
        String subject = "Customer Info Update";
        String emailBody = "Dear " + customer.getCustomerName() + "," + "\n" + "\n" + "Your information has been successfully updated. Continue enjoying our services." + "\n" + "***Thank you for choosing us***" + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";
        emailSender.sendMail(sendTo, subject, emailBody);
    }

}
