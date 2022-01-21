/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Data;

/**
 *
 * @author collins
 */
@Entity
@Data
@Table(name = "customers")
public class Customer implements Serializable{

    @Id
    private Long customerId;
    private String customerName;
    private String email;
    private String mobileNo;
    private String sex;
    private LocalDateTime  registeredOn;
    private String status;


     }
