/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.controller;

import com.loan.vehicle.model.VehicleMake;
import com.loan.vehicle.service.VehicleMakeService;
import java.util.List;
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
@RequestMapping(value = "/vehicle/make", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleMakeController {
    
     @Autowired
    VehicleMakeService vehicleMakeService;

    @GetMapping("/list")
    public List<VehicleMake> getAllVehiclesMakes() {
       
        return vehicleMakeService.getAllVehicleMakes();

        
    }
   
    
     @PostMapping("/add")
    public VehicleMake addVehicleMake(@RequestBody VehicleMake vehicleMake) throws Exception {
        vehicleMakeService.saveVehicleMake(vehicleMake);
        return vehicleMake;
    }
    


    @PutMapping("update/{id}")
    public VehicleMake updateVehicleMake(@PathVariable long id, @RequestBody VehicleMake vehicleMake)throws Exception {
        
        vehicleMakeService.updateVehicleMake(vehicleMake,id);
    return vehicleMakeService.getVehicleMakeById(id);
    }

    @GetMapping("view/{id}")
    public VehicleMake viewVehicleMake(@PathVariable long id) {
      return vehicleMakeService.getVehicleMakeById(id);
        

         
    }

    @DeleteMapping("delete/{id}")
    public VehicleMake delete(@PathVariable long id) {

        vehicleMakeService.deleteVehicleMake(id);
        return new VehicleMake();

    }
    
}
