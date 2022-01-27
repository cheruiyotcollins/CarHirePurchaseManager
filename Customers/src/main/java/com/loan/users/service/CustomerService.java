/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.service;

import com.loan.users.configs.QueueConfigs;
import com.loan.users.model.Customer;
import com.loan.users.model.CustomerPassportPhotos;
import com.loan.users.model.FailedEmails;
import com.loan.users.repository.CustomerRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;

import com.loan.users.repository.FailedEmailsRepository;
import com.loan.users.repository.PhotosRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.ArrayUtils;

import static org.apache.tomcat.util.codec.binary.Base64.*;


/**
 * @author collins
 */
@Service
@Component
@Transactional

public class CustomerService implements ErrorHandler {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PhotosRepository photosRepository;
    @Value("${new-customer-subject}")
    private String newCustomerEmailSubject;

    @Value("${updated-customer-subject}")
    private String updateCustomerEmailSubject;

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

    public Customer getCustomerById(long id) {
           return customerRepository.findById(id).get();
    }

    public boolean customerExistById(long id) {
        LOGGER.info("*******checking if customer exist **");
        return customerRepository.existsById(id);
    }

    //@Async("asyncExecutor")
    @JmsListener(destination = QueueConfigs.addCustomer)
    public void saveCustomer(Message message) throws JMSException, IOException, ClassNotFoundException {
        LOGGER.info("*******Incoming request to save customer from activeMQ " + message);
        final BytesMessage customerBytes = (BytesMessage) message;
        final byte[] array = new byte[Long.valueOf(customerBytes.getBodyLength()).intValue()];
        customerBytes.readBytes(array);
        final ByteArrayInputStream bIn = new ByteArrayInputStream(array);
        final ObjectInputStream oIn = new ObjectInputStream(bIn);
        Customer customer = (Customer) oIn.readObject();

        LOGGER.info("******* Decoded customer from activeMQ " + customer);
        customer.setRegisteredOn(LocalDateTime.now());
        customerRepository.save(customer);
        LOGGER.info("*******Saved successfully**");

        String emailBody = "Dear " + customer.getCustomerName() + "," + "\n" + "\n" + "Your have been successfully onboarded to Collins Motor Ltd platform. You can now apply for a car loan and pay in a period of upto 3 months." + "\n" + "**Thank you for choosing us**" + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";
        emailSender.sendMail(customer.getEmail(), newCustomerEmailSubject, emailBody);



    }

    @Override
    public void handleError(Throwable t) {
        LOGGER.error("Error in listener", t);
    }

    @Transactional

    public void saveImageFile(Long photoId, MultipartFile file) {

        try {
            CustomerPassportPhotos image = new CustomerPassportPhotos();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            image.setPhoto(byteObjects);
            image.setPhotoId(photoId);
            photosRepository.save(image);
            LOGGER.info("*******Photo uploaded successfully **");
        } catch (IOException e) {

            //log.error("Error occurred", e);

            e.printStackTrace();
        }
    }

    //TODO convert Byte to multipart
    public Byte[] getImageFileById(Long photoId) {


        CustomerPassportPhotos image = photosRepository.findById(photoId).get();
        Byte[] imageByte = image.getPhoto();

        byte[] byteImage = ArrayUtils.toPrimitive(imageByte);

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return byteImage == null || byteImage.length == 0;
            }

            @Override
            public long getSize() {
                return byteImage.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return byteImage;
            }

            @Override
            public InputStream getInputStream() throws IOException {

                return new ByteArrayInputStream(byteImage);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                new FileOutputStream(dest).write(byteImage);
            }
        };


        LOGGER.info("*******Photo fetched successfully **");
        return imageByte;


    }


    @Async("asyncExecutor")
    public void updateCustomer(Customer customer, Long id) {
        customer.setRegisteredOn(LocalDateTime.now());
        customerRepository.save(customer);
        String emailBody = "Dear " + customer.getCustomerName() + "," + "\n" + "\n" + "Your information has been successfully updated. Continue enjoying our services." + "\n" + "***Thank you for choosing us***" + "\n\n"
                + "Regards" + "\n" + "Collins Motors Ltd";

        emailSender.sendMail(customer.getEmail(), updateCustomerEmailSubject, emailBody);
       }

    @Async("asyncExecutor")
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);

    }





}
