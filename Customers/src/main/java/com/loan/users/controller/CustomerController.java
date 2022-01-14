/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.controller;

import com.loan.users.dto.CustomerExist;
import com.loan.users.dto.StringDto;
import com.loan.users.model.Customer;
import com.loan.users.service.CustomerService;
import java.util.List;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author collins
 */
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    CustomerService customerService;
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    JmsTemplate jmsTemplate;
    
    @Autowired
    ActiveMQQueue activeMQQueue;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getAllCustomers() {
          // jmsTemplate.convertAndSend(activeMQQueue, "hey there **************");
           
        return customerService.getAllCustomers();

    }
     @GetMapping("/active")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getActiveCustomers() {
           
        return customerService.getAllActiveCustomers();

    }
    @GetMapping("/inactive")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getInactiveCustomers() {
           
        return customerService.getAllInactiveCustomers();

    }
    @GetMapping("/male")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getMaleCustomers() {
           
        return customerService.getAllMaleCustomers();

    }
    @GetMapping("/female")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getFemaleCustomers() {
           
        return customerService.getAllFemaleCustomers();

    }
    
     @GetMapping("/registered/on/{onDate}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getRegisteredOnCustomers(@PathVariable String onDate) {
           
        return customerService.getAllCustomersRegisteredOn(onDate);

    }
     @GetMapping("/registered/between/{fromDate}/{toDate}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> getRegisteredBetweenCustomers(@PathVariable String fromDate,@PathVariable String toDate) {
           
        return customerService.getAllCustomersRegisteredBetween(fromDate,toDate);

    }
      @GetMapping("/search/name/{names}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Customer> searchCustomerName(@PathVariable String names) {
           
        return customerService.getCustomersByName(names);

    }
    

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer addCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer customer){

        customerService.updateCustomer(customer, id);
        return customerService.getCustomerById(id);
    }

    @GetMapping("/view/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer viewCustomerById(@PathVariable long id) {

        return customerService.getCustomerById(id);

    }

    @GetMapping("/exist/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public CustomerExist existById(@PathVariable long id) {
        CustomerExist customerExist = new CustomerExist();
        customerExist.existById = customerService.customerExistById(id);
        LOGGER.info("*******Customer Exist? " + customerExist.toString());
        return customerExist;

    }
    @GetMapping("/email/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public StringDto nameNMail(@PathVariable long id) {
        StringDto stringDto = new StringDto();
        stringDto.email= customerService.getCustomerById(id).getEmail();
        stringDto.name= customerService.getCustomerById(id).getCustomerName();
        LOGGER.info("*******retriving customer  name and email " + stringDto);
        return stringDto;

    }
    
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer delete(@PathVariable long id) {

        customerService.deleteCustomer(id);
        return new Customer();

    }

}
