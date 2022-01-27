/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.manager.loan.loans.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author collins
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GeneralResponse {
    public HttpStatus status;
    public String description;
    
}
