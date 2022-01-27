/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.controller;

import com.loan.vehicle.model.Vehicle;
import com.loan.vehicle.service.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController {
      @Autowired
    VehicleService vehicleService;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Vehicle> getAllVehicles() {
       
        return vehicleService.getAllVehicles();

        
    }
   
    
     @PostMapping("/add")
     @CrossOrigin(origins = "http://localhost:3000")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) throws Exception {
        vehicleService.saveVehicle(vehicle);
        return vehicle;
    }
    


    @PutMapping("update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Vehicle updateVehicle(@PathVariable long id, @RequestBody Vehicle vehicle)throws Exception {
        
        vehicleService.updateVehicle(vehicle,id);
    return vehicleService.getVehicleById(id);
    }

    @GetMapping("view/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Vehicle viewVehicle(@PathVariable long id) {

        return vehicleService.getVehicleById(id);

         
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Vehicle delete(@PathVariable long id) {

        vehicleService.deleteVehicle(id);
        return new Vehicle();

    }
    
}
