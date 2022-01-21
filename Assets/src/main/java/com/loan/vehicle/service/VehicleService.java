/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.service;

import com.loan.vehicle.model.Vehicle;
import com.loan.vehicle.repository.VehicleRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author collins
 */
@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    private static final String CUSTOMERURL = "http://localhost:9090/asset";
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    //Crud Operations
    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
        
    }
    
    public Vehicle getVehicleById(long id) {
        LOGGER.info("*******Incomming message request from loan microservice");
        return vehicleRepository.findById(id).get();
    }
    
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void updateVehicle(Vehicle vehicle, Long id) {
        
        vehicle.setVehicleId(id);
        vehicleRepository.save(vehicle);
    }
    
    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
        
    }
    
}
