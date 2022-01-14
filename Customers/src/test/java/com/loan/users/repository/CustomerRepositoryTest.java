/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.loan.users.repository;

import com.loan.users.model.Customer;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

/**
 *
 * @author hp pc
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    
    public CustomerRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findAllActiveCustomers method, of class CustomerRepository.
     */
    @Test
    public void testFindAllActiveCustomers() {
        System.out.println("findAllActiveCustomers");
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findAllActiveCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllInactiveCustomers method, of class CustomerRepository.
     */
    @Test
    public void testFindAllInactiveCustomers() {
        System.out.println("findAllInactiveCustomers");
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findAllInactiveCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllMaleCustomers method, of class CustomerRepository.
     */
    @Test
    public void testFindAllMaleCustomers() {
        System.out.println("findAllMaleCustomers");
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findAllMaleCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllFemaleCustomers method, of class CustomerRepository.
     */
    @Test
    public void testFindAllFemaleCustomers() {
        System.out.println("findAllFemaleCustomers");
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findAllFemaleCustomers();
        assertFalse(result.isEmpty());
        
    }

    /**
     * Test of findCustomersRegisteredOn method, of class CustomerRepository.
     */
    @Test
    public void testFindCustomersRegisteredOn() {
        System.out.println("findCustomersRegisteredOn");
        String registeredOn = "";
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findCustomersRegisteredOn(registeredOn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomersRegisteredBetween method, of class CustomerRepository.
     */
    @Test
    public void testFindCustomersRegisteredBetween() {
        System.out.println("findCustomersRegisteredBetween");
        String fromDate = "";
        String toDate = "";
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findCustomersRegisteredBetween(fromDate, toDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomerByName method, of class CustomerRepository.
     */
    @Test
    public void testFindCustomerByName() {
        System.out.println("findCustomerByName");
        String customerName = "";
        CustomerRepository instance = new CustomerRepositoryImpl();
        Collection<Customer> expResult = null;
        Collection<Customer> result = instance.findCustomerByName(customerName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CustomerRepositoryImpl implements CustomerRepository {

        public Collection<Customer> findAllActiveCustomers() {
            return null;
        }

        public Collection<Customer> findAllInactiveCustomers() {
            return null;
        }

        public Collection<Customer> findAllMaleCustomers() {
            return null;
        }

        public Collection<Customer> findAllFemaleCustomers() {
            return null;
        }

        public Collection<Customer> findCustomersRegisteredOn(String registeredOn) {
            return null;
        }

        public Collection<Customer> findCustomersRegisteredBetween(String fromDate, String toDate) {
            return null;
        }

        public Collection<Customer> findCustomerByName(String customerName) {
            return null;
        }

        @Override
        public List<Customer> findAll() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Customer> findAll(Sort sort) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Customer> findAllById(Iterable<Long> ids) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void flush() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> S saveAndFlush(S entity) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAllInBatch(Iterable<Customer> entities) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ids) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAllInBatch() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Customer getOne(Long id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Customer getById(Long id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> List<S> findAll(Example<S> example) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Page<Customer> findAll(Pageable pageable) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> S save(S entity) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Optional<Customer> findById(Long id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean existsById(Long id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public long count() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteById(Long id) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void delete(Customer entity) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAllById(Iterable<? extends Long> ids) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAll(Iterable<? extends Customer> entities) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void deleteAll() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> Optional<S> findOne(Example<S> example) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> long count(Example<S> example) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer> boolean exists(Example<S> example) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
