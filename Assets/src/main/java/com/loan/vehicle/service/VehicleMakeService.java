/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.service;

import com.loan.vehicle.model.VehicleMake;
import com.loan.vehicle.repository.VehicleMakeRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author collins
 */
@Service
public class VehicleMakeService {
       @Autowired
    VehicleMakeRepository  vehicleMakeRepository;
    private static final String CUSTOMERURL="http://localhost:9090/asset";
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    
    
    
    
    //Crud Operations
    public List<VehicleMake> getAllVehicleMakes(){
        return (List<VehicleMake>) vehicleMakeRepository.findAll();
        
    }

    public VehicleMake getVehicleMakeById(long id){
    return  vehicleMakeRepository.findById(id).get();
    }

    public void saveVehicleMake(VehicleMake vehicleMake){
    vehicleMakeRepository.save(vehicleMake);
    }
    public void updateVehicleMake(VehicleMake vehicleMake, Long id){
    vehicleMakeRepository.save(vehicleMake);
    }

    public void deleteVehicleMake(long id){
        vehicleMakeRepository.deleteById(id);
    
    }
}
