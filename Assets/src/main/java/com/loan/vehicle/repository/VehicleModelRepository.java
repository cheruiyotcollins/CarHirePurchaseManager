/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.vehicle.repository;

import com.loan.vehicle.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author collins
 */
public interface VehicleModelRepository extends JpaRepository<VehicleModel,Long>{
    
}
