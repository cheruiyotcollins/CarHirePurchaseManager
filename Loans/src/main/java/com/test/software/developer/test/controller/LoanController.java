/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.software.developer.test.controller;

import com.test.software.developer.test.configs.QueueConfigs;
import com.test.software.developer.test.dto.GeneralResponse;
import com.test.software.developer.test.model.LoanModel;
import com.test.software.developer.test.service.LoanService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author collins
 */
@RestController
@RequestMapping(value = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    @Autowired
    JmsTemplate jmsTemplate;
    GeneralResponse generalResponse;


    @Autowired
    LoanService loanService;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getAllLoans() {

        return loanService.getAllLoans();


    }


    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity addLoan(@RequestBody LoanModel loanModel) throws Exception {
        generalResponse = new GeneralResponse();
        generalResponse.setDescription("your request is being processed");
        generalResponse.setStatus(HttpStatus.PROCESSING);
        LOGGER.info("received for queue:::::" + QueueConfigs.processLoan+ loanModel);
        this.jmsTemplate.send(QueueConfigs.processLoan, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                final ObjectOutputStream oOut;
                try {
                    oOut = new ObjectOutputStream(bOut);
                    oOut.writeObject(loanModel); // where object is the object to serialize
                    BytesMessage message = session.createBytesMessage();
                    message.writeBytes(bOut.toByteArray());
                    return message;
                } catch (IOException e) {
                    e.printStackTrace();

                }
                return null;
            }
        });
        return ResponseEntity.ok(generalResponse);
    }


    @PutMapping("update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public LoanModel updateLoan(@PathVariable long id, @RequestBody LoanModel loan) throws Exception {

        loanService.updateLoan(loan, id);
        return loanService.getLoanById(id);
    }

    @GetMapping("view/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public LoanModel viewLoan(@PathVariable long id) {

        LoanModel personDetails = loanService.getLoanById(id);

        return personDetails;
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public LoanModel delete(@PathVariable long id) {

        loanService.deleteLoan(id);
        return new LoanModel();

    }


    @GetMapping("/vehicle_make/{vehicleId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoanByVehicleMake(@PathVariable("vehicleId") Long vehicleId) {

        return loanService.getLoansByVehicleMake(vehicleId);

    }

    @GetMapping("/due/on/{dueDate}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoanDueOn(@PathVariable("dueDate") String dueDate) {

        return loanService.getLoansDueOn(dueDate);

    }

    @GetMapping("/duration/{duration}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoansByDuration(@PathVariable("duration") int duration) {

        return loanService.getLoansByDuration(duration);

    }

    @GetMapping("/more_than/{moreThan}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoansMoreThan(@PathVariable("moreThan") double moreThan) {

        return loanService.getLoansMoreThan(moreThan);

    }

    @GetMapping("/less_than/{lessThan}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoansLessThan(@PathVariable("lessThan") double lessThan) {

        return loanService.getLoansLessThan(lessThan);

    }

    @GetMapping("/disbursed/between/{fromDate}/{toDate}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoansRegisteredBetween(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {

        return loanService.getLoansDusbursedBetween(fromDate, toDate);

    }

    @GetMapping("/disbursed_on/{onDate}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoansDisbursedOn(@PathVariable("onDate") String onDate) {

        return loanService.getLoansDisbursedOn(onDate);

    }

    @GetMapping("/customer_id/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<LoanModel> getLoansByCustomerId(@PathVariable("id") Long id) {

        return loanService.getLoanByCustomerId(id);

    }


}
