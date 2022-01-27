/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.service;

import com.loan.vehicle.model.VehicleModel;
import com.loan.vehicle.repository.VehicleModelRepository;
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
public class VehicleModelService {
     @Autowired
    VehicleModelRepository  vehicleModelRepository;
    private static final String CUSTOMERURL="http://localhost:9090/asset";
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    
    
    
    
    //Crud Operations
    public List<VehicleModel> getAllVehicleModels(){
        return (List<VehicleModel>) vehicleModelRepository.findAll();
        
    }

    public VehicleModel getVehicleModelById(long id){
    return  vehicleModelRepository.findById(id).get();
    }
    
    public boolean existById(long id){
    return  vehicleModelRepository.existsById(id);
    }

    public void saveVehicleModel(VehicleModel vehicleModel){
    vehicleModelRepository.save(vehicleModel);
    }
    public void updateVehicleModel(VehicleModel vehicleModel, Long id){
    vehicleModelRepository.save(vehicleModel);
    }

    public void deleteVehicleModel(long id){
        vehicleModelRepository.deleteById(id);
    
    }
    
}
