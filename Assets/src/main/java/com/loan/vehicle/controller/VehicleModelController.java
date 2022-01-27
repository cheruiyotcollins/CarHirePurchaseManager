/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.controller;

import com.loan.vehicle.model.VehicleModel;
import com.loan.vehicle.service.VehicleModelService;
import com.loan.vehicle.service.VehicleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/vehicle/model", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleModelController {
     @Autowired
    VehicleModelService vehicleModelService;
 private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    @GetMapping("/list")
    public List<VehicleModel> getAllVehicleModels() {
       
        return vehicleModelService.getAllVehicleModels();

        
    }
   
    
     @PostMapping("/add")
    public VehicleModel addVehicleModel(@RequestBody VehicleModel vehicleModel) throws Exception {
        vehicleModelService.saveVehicleModel(vehicleModel);
        return vehicleModel;
    }
    


    @PutMapping("update/{id}")
    public VehicleModel updateVehicleModel(@PathVariable long id, @RequestBody VehicleModel vehicleModel)throws Exception {
        
        vehicleModelService.updateVehicleModel(vehicleModel,id);
    return vehicleModelService.getVehicleModelById(id);
    }

    @GetMapping("view/{id}")
    public VehicleModel viewVehicleModel(@PathVariable long id) {
       
      return vehicleModelService.getVehicleModelById(id);
      
        
         
    }
    @GetMapping("exist/{id}")
    public Boolean existById(@PathVariable long id) {
        LOGGER.info("*******Vehicle Exist? "+vehicleModelService.existById(id));
        return vehicleModelService.existById(id);
         
    }

    @DeleteMapping("delete/{id}")
    public VehicleModel delete(@PathVariable long id) {

        vehicleModelService.deleteVehicleModel(id);
        return new VehicleModel();

    }
    
    
}
