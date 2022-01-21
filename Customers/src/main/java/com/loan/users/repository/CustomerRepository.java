/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.loan.users.repository;

import com.loan.users.model.Customer;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author collins
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(
            value = "SELECT * FROM customers c WHERE c.status = 'Active'",
            nativeQuery = true)
    Collection<Customer> findAllActiveCustomers();

    @Query(
            value = "SELECT * FROM customers c WHERE c.status = 'Inactive'",
            nativeQuery = true)
    Collection<Customer> findAllInactiveCustomers();

    @Query(
            value = "SELECT * FROM customers c WHERE c.sex = 'Male'",
            nativeQuery = true)
    Collection<Customer> findAllMaleCustomers();

    @Query(
            value = "SELECT * FROM customers c WHERE c.sex = 'Female'",
            nativeQuery = true)
    Collection<Customer> findAllFemaleCustomers();

    @Query(
            value = "SELECT * FROM customers c WHERE c.registered_on LIKE :registeredOn",
            nativeQuery = true)
    Collection<Customer> findCustomersRegisteredOn(@Param("registeredOn") String registeredOn);

    @Query(
            value = "SELECT * FROM customers c WHERE c.registered_on BETWEEN :fromD AND :toD",
            nativeQuery = true)
    Collection<Customer> findCustomersRegisteredBetween(@Param("fromD") String fromDate, @Param("toD") String toDate);
    
       @Query(
            value = "SELECT * FROM customers c WHERE c.customer_name like :name",
            nativeQuery = true)
    Collection<Customer> findCustomerByName(@Param("name") String customerName);

}
    
    

