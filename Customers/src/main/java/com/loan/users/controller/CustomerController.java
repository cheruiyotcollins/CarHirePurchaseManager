/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.controller;

import com.loan.users.configs.QueueConfigs;
import com.loan.users.dto.GeneralResponse;
import com.loan.users.dto.StringDto;
import com.loan.users.model.Customer;
import com.loan.users.service.CustomerService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 *
 * @author collins
 */
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    JmsTemplate jmsTemplate;

    GeneralResponse generalResponse;
    QueueConfigs queueConfigs;

    @GetMapping("/list")
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();

    }
    @PostMapping("/add")
    public void addCustomer(@RequestBody Customer customer){
        generalResponse= new GeneralResponse();
        generalResponse.setDescription("your request is being processed");
        generalResponse.setStatus(HttpStatus.PROCESSING);

        this.jmsTemplate.send(QueueConfigs.addCustomer, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                final ObjectOutputStream oOut;
                try {
                    oOut = new ObjectOutputStream(bOut);
                    oOut.writeObject(customer); // where object is the object to serialize
                    BytesMessage message = session.createBytesMessage();
                    message.writeBytes(bOut.toByteArray());
                    return message;
                } catch (IOException e) {
                    e.printStackTrace();

                }
                 return null;
            }
        });


        LOGGER.info("adding save customer request to queue"+QueueConfigs.addCustomer);


    }
     @GetMapping("/active")
    public List<Customer> getActiveCustomers() {
           
        return customerService.getAllActiveCustomers();

    }
    @GetMapping("/inactive")
    public List<Customer> getInactiveCustomers() {
           
        return customerService.getAllInactiveCustomers();

    }
    @GetMapping("/male")

    public List<Customer> getMaleCustomers() {
           
        return customerService.getAllMaleCustomers();

    }
    @GetMapping("/female")
    public List<Customer> getFemaleCustomers() {
           
        return customerService.getAllFemaleCustomers();

    }
    
     @GetMapping("/registered/on/{onDate}")
    public List<Customer> getRegisteredOnCustomers(@PathVariable String onDate) {
           
        return customerService.getAllCustomersRegisteredOn(onDate);

    }
     @GetMapping("/registered/between/{fromDate}/{toDate}")
    public List<Customer> getRegisteredBetweenCustomers(@PathVariable String fromDate,@PathVariable String toDate) {
           
        return customerService.getAllCustomersRegisteredBetween(fromDate,toDate);

    }
      @GetMapping("/search/name/{names}")
        public List<Customer> searchCustomerName(@PathVariable String names) {
           
        return customerService.getCustomersByName(names);

    }
    


    @PostMapping("photo/add/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void handleImagePost(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        LOGGER.info("*******a request to save photo");
        System.out.printf("File name=%s, size=%s\n", file.getOriginalFilename(),file.getSize());
        customerService.saveImageFile(id, file);


    }
    // TODO convert Byte to Multipart
    @GetMapping("photo/get/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Byte[] handleImageGet(@PathVariable Long id ){
        LOGGER.info("*******a request to fetch photo");

      return  customerService.getImageFileById(id);


    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void updateCustomer(@PathVariable long id, @RequestBody Customer customer){

        customerService.updateCustomer(customer, id);
        //return customerService.getCustomerById(id).;
    }

    @GetMapping("/view/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer viewCustomerById(@PathVariable long id) {
        LOGGER.info("*******fetch customer by id**"+customerService.getCustomerById(id));
        return customerService.getCustomerById(id);

    }

    @GetMapping("/exist/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Boolean existById(@PathVariable long id) {
       LOGGER.info("*******Customer Exist? " + customerService.customerExistById(id));
        return customerService.customerExistById(id);

    }
    @GetMapping("/email/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public StringDto nameNMail(@PathVariable long id) {
        StringDto stringDto = new StringDto();
        stringDto.email= customerService.getCustomerById(id).getEmail();
        stringDto.name= customerService.getCustomerById(id).getCustomerName();
        LOGGER.info("*******retrieving customer  name and email " + stringDto);
        return stringDto;

    }
    
    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Customer delete(@PathVariable long id) {

        customerService.deleteCustomer(id);
        return new Customer();

    }

}
